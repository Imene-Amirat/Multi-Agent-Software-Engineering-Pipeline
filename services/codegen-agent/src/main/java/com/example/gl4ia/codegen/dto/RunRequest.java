package com.example.gl4ia.codegen.dto;

import java.util.Map;

public record RunRequest(
        String goal,
        Map<String, Object> state
) {}
