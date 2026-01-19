package com.example.gl4ia.architecture.controller;

import com.example.gl4ia.architecture.dto.RunRequest;
import com.example.gl4ia.architecture.dto.RunResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class ArchitectureAgentController {

    @PostMapping("/run")
    public RunResponse run(@RequestBody RunRequest request) {

        Map<String, Object> architecture = Map.of(
                "style", "microservices",
                "components", List.of(
                        "orchestrator-service",
                        "requirements-agent",
                        "architecture-agent",
                        "codegen-agent",
                        "testing-agent",
                        "quality-agent",
                        "cicd-agent",
                        "memory-service",
                        "observability-service"
                ),
                "communication", "HTTP REST (synchronous)",
                "workflow", List.of(
                        "goal -> requirements-agent",
                        "specs -> architecture-agent",
                        "design -> codegen-agent",
                        "code -> testing-agent",
                        "tests -> quality-agent",
                        "quality -> cicd-agent"
                ),
                "decisions", List.of(
                        "Stateless agents",
                        "Centralized memory-service",
                        "Centralized observability-service",
                        "Spring Boot microservices",
                        "Docker Compose for local orchestration"
                )
        );

        return new RunResponse(
                Map.of("architecture", architecture),
                "Architecture and workflow generated based on requirements"
        );
    }

    @GetMapping("/health")
    public Map<String, Object> health() {
        return Map.of("ok", true);
    }
}
