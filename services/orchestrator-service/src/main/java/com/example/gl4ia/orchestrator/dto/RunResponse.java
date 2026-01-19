package com.example.gl4ia.orchestrator.dto;

import java.util.Set;

public record RunResponse(
        String status,
        String runDir,
        Set<String> stateKeys
) {}
