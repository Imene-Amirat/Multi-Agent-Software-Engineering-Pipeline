package com.example.gl4ia.orchestrator.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "agents")
public record AgentsProperties(
        String memoryUrl,
        String observabilityUrl,
        String requirementsUrl,
        String architectureUrl,
        String codegenUrl,
        String testingUrl,
        String qualityUrl,
        String cicdUrl
) {}
