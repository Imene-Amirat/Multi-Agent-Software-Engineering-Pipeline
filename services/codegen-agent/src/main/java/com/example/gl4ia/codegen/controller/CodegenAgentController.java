package com.example.gl4ia.codegen.controller;

import com.example.gl4ia.codegen.dto.RunRequest;
import com.example.gl4ia.codegen.dto.RunResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping
public class CodegenAgentController {

    @PostMapping("/run")
    public RunResponse run(@RequestBody RunRequest request) {

        // Simple demo code generation (Java Calculator)
        String calculatorCode = """
                package demo_app;

                public class Calculator {

                    public static double add(double a, double b) {
                        return a + b;
                    }

                    public static double sub(double a, double b) {
                        return a - b;
                    }

                    public static double mul(double a, double b) {
                        return a * b;
                    }

                    public static double div(double a, double b) {
                        if (b == 0) {
                            throw new IllegalArgumentException("division by zero");
                        }
                        return a / b;
                    }
                }
                """;

        Map<String, Object> artifacts = Map.of(
                "src/main/java/demo_app/Calculator.java", calculatorCode
        );

        return new RunResponse(
                Map.of("artifacts", artifacts),
                "Generated Calculator Java source code"
        );
    }

    @GetMapping("/health")
    public Map<String, Object> health() {
        return Map.of("ok", true);
    }
}
