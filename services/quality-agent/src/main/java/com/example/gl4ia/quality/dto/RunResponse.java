package com.example.gl4ia.quality.dto;

import java.util.Map;

public record RunResponse(
        Map<String, Object> patch,
        String notes
) {}
