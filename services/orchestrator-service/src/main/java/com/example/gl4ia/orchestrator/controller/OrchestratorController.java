package com.example.gl4ia.orchestrator.controller;

import com.example.gl4ia.orchestrator.config.AgentsProperties;
import com.example.gl4ia.orchestrator.dto.RunRequest;
import com.example.gl4ia.orchestrator.dto.RunResponse;
import com.example.gl4ia.orchestrator.http.HttpClient;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping
public class OrchestratorController {

    private final HttpClient http;
    private final AgentsProperties agents;

    public OrchestratorController(HttpClient http, AgentsProperties agents) {
        this.http = http;
        this.agents = agents;
    }

    @PostMapping("/run")
    public RunResponse run(@RequestBody RunRequest request) {

        String goal = request.goal();
        event("orchestrator", "start", Map.of("goal", goal));

        ensureArtifacts();

        callAgent("requirements", agents.requirementsUrl(), goal);
        callAgent("architecture", agents.architectureUrl(), goal);
        callAgent("codegen", agents.codegenUrl(), goal);
        callAgent("testing", agents.testingUrl(), goal);
        callAgent("quality", agents.qualityUrl(), goal);
        callAgent("cicd", agents.cicdUrl(), goal);

        Map<String, Object> state = getState();
        String runDir = "runs/" + Instant.now().toString().replace(":", "-");
        writeArtifacts(state, runDir);

        event("orchestrator", "end", Map.of("runDir", runDir));
        return new RunResponse("done", runDir, state.keySet());
    }

    /* ------------------ helpers ------------------ */

    private void callAgent(String name, String url, String goal) {
        event("orchestrator", "call_agent", Map.of("agent", name));

        Map<String, Object> state = getState();
        Map<String, Object> body = Map.of("goal", goal, "state", state);

        Map<?, ?> response = http.post(url + "/run", body, Map.class);
        Map<String, Object> patch = (Map<String, Object>) response.get("patch");

        if (patch == null) return;

        if (patch.containsKey("artifacts")) {
            Map<String, Object> current = (Map<String, Object>)
                    getState().getOrDefault("artifacts", new HashMap<>());
            current.putAll((Map<String, Object>) patch.get("artifacts"));
            patch = new HashMap<>(patch);
            patch.put("artifacts", current);
        }

        for (var e : patch.entrySet()) {
            putState(e.getKey(), e.getValue());
        }

        event(name, "done", Map.of("keys", patch.keySet()));
    }

    private Map<String, Object> getState() {
        return http.get(agents.memoryUrl() + "/state", Map.class);
    }

    private void putState(String key, Object value) {
        http.post(agents.memoryUrl() + "/state",
                Map.of("key", key, "value", value), Map.class);
    }

    private void ensureArtifacts() {
        Map<String, Object> state = getState();
        if (!state.containsKey("artifacts")) {
            putState("artifacts", new HashMap<String, Object>());
        }
    }

    private void event(String service, String step, Map<String, Object> payload) {
        http.post(agents.observabilityUrl() + "/event",
                Map.of("service", service, "step", step, "payload", payload),
                Map.class);
    }

    private void writeArtifacts(Map<String, Object> state, String runDir) {
        Object a = state.get("artifacts");
        if (!(a instanceof Map<?, ?> artifacts)) return;

        Path base = Paths.get(runDir);
        try { Files.createDirectories(base); } catch (Exception ignored) {}

        artifacts.forEach((path, content) -> {
            try {
                Path p = base.resolve(path.toString());
                Files.createDirectories(p.getParent());
                Files.writeString(p, content.toString(), StandardCharsets.UTF_8);
            } catch (Exception ignored) {}
        });
    }

    @GetMapping("/health")
    public Map<String, Object> health() {
        return Map.of("ok", true);
    }
}
