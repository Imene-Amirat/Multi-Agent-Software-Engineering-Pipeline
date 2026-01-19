package com.example.gl4ia.orchestrator;

import com.example.gl4ia.orchestrator.config.AgentsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(AgentsProperties.class)
@SpringBootApplication
public class OrchestratorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrchestratorServiceApplication.class, args);
	}

}
