package com.example.gl4ia.requirements.dto;

import java.util.Map;

public record RunRequest(
        String goal,
        Map<String, Object> state
) {}
