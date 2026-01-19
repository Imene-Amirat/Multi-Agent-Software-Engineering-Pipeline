package com.example.gl4ia.quality.controller;

import com.example.gl4ia.quality.dto.RunRequest;
import com.example.gl4ia.quality.dto.RunResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class QualityAgentController {

    @PostMapping("/run")
    public RunResponse run(@RequestBody RunRequest request) {

        Map<String, Object> qualityReport = Map.of(
                "checks", List.of(
                        "code_style",
                        "complexity",
                        "naming_conventions",
                        "test_coverage"
                ),
                "results", List.of(
                        "No critical issues found",
                        "Code complexity acceptable",
                        "Naming conventions respected"
                ),
                "recommendations", List.of(
                        "Add JavaDoc comments",
                        "Increase test coverage for edge cases",
                        "Integrate static analysis tool (SonarQube)"
                )
        );

        return new RunResponse(
                Map.of("quality_report", qualityReport),
                "Quality analysis completed successfully"
        );
    }

    @GetMapping("/health")
    public Map<String, Object> health() {
        return Map.of("ok", true);
    }
}
