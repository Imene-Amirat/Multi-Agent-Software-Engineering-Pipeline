package com.example.gl4ia.codegen.dto;

import java.util.Map;

public record RunResponse(
        Map<String, Object> patch,
        String notes
) {}
