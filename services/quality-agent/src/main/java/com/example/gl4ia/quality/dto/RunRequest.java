package com.example.gl4ia.quality.dto;

import java.util.Map;

public record RunRequest(
        String goal,
        Map<String, Object> state
) {}
