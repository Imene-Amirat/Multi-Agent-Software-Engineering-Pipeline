package com.example.gl4ia.cicd.controller;

import com.example.gl4ia.cicd.dto.RunRequest;
import com.example.gl4ia.cicd.dto.RunResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping
public class CicdAgentController {

    @PostMapping("/run")
    public RunResponse run(@RequestBody RunRequest request) {

        String githubActions = """
                name: Java CI

                on:
                  push:
                  pull_request:

                jobs:
                  build:
                    runs-on: ubuntu-latest
                    steps:
                      - uses: actions/checkout@v4
                      - name: Set up JDK 17
                        uses: actions/setup-java@v4
                        with:
                          java-version: '17'
                          distribution: 'temurin'
                      - name: Build with Maven
                        run: mvn -q test
                """;

        Map<String, Object> artifacts = Map.of(
                ".github/workflows/ci.yml", githubActions
        );

        return new RunResponse(
                Map.of("artifacts", artifacts),
                "CI/CD pipeline generated (GitHub Actions)"
        );
    }

    @GetMapping("/health")
    public Map<String, Object> health() {
        return Map.of("ok", true);
    }
}
