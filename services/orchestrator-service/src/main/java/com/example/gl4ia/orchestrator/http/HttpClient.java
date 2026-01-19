package com.example.gl4ia.orchestrator.http;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public <T> T get(String url, Class<T> clazz) {
        return restTemplate.getForObject(url, clazz);
    }

    public <T> T post(String url, Object body, Class<T> clazz) {
        return restTemplate.postForObject(url, body, clazz);
    }
}
