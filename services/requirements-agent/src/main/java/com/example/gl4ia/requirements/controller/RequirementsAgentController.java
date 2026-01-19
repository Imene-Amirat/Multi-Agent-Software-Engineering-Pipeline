package com.example.gl4ia.requirements.controller;

import com.example.gl4ia.requirements.dto.RunRequest;
import com.example.gl4ia.requirements.dto.RunResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class RequirementsAgentController {

    @PostMapping("/run")
    public RunResponse run(@RequestBody RunRequest request) {

        Map<String, Object> specs = Map.of(
                "user_story", "As a user, I want to " + request.goal(),
                "acceptance_criteria", List.of(
                        "Given valid inputs When operation is executed Then expected result is returned",
                        "Given invalid inputs When operation is executed Then an error is raised"
                ),
                "assumptions", List.of(
                        "Demo scope only",
                        "Single-user scenario",
                        "Artifacts stored in memory-service"
                )
        );

        return new RunResponse(
                Map.of("specs", specs),
                "Structured requirements generated from goal"
        );
    }

    @GetMapping("/health")
    public Map<String, Object> health() {
        return Map.of("ok", true);
    }
}
