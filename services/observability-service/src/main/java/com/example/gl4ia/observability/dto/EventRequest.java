package com.example.gl4ia.observability.dto;

import java.util.Map;

public record EventRequest(
        String service,
        String step,
        Map<String, Object> payload
) {}
