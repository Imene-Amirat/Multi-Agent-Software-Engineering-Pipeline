package com.example.gl4ia.requirements.dto;

import java.util.Map;

public record RunResponse(
        Map<String, Object> patch,
        String notes
) {}
