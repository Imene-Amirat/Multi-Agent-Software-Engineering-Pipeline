package com.example.gl4ia.testing.controller;

import com.example.gl4ia.testing.dto.RunRequest;
import com.example.gl4ia.testing.dto.RunResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping
public class TestingAgentController {

    @PostMapping("/run")
    public RunResponse run(@RequestBody RunRequest request) {

        // JUnit test generation for Calculator
        String testCode = """
                package demo_app;

                import org.junit.jupiter.api.Test;
                import static org.junit.jupiter.api.Assertions.*;

                public class CalculatorTest {

                    @Test
                    void testAdd() {
                        assertEquals(5, Calculator.add(2, 3));
                    }

                    @Test
                    void testSub() {
                        assertEquals(2, Calculator.sub(5, 3));
                    }

                    @Test
                    void testMul() {
                        assertEquals(8, Calculator.mul(2, 4));
                    }

                    @Test
                    void testDiv() {
                        assertEquals(4, Calculator.div(8, 2));
                    }

                    @Test
                    void testDivByZero() {
                        assertThrows(IllegalArgumentException.class,
                                () -> Calculator.div(1, 0));
                    }
                }
                """;

        Map<String, Object> artifacts = Map.of(
                "src/test/java/demo_app/CalculatorTest.java", testCode
        );

        return new RunResponse(
                Map.of("artifacts", artifacts),
                "Generated JUnit tests for Calculator"
        );
    }

    @GetMapping("/health")
    public Map<String, Object> health() {
        return Map.of("ok", true);
    }
}
