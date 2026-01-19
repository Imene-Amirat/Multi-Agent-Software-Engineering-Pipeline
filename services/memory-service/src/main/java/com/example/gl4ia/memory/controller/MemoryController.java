package com.example.gl4ia.memory.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping
public class MemoryController {

    private final Map<String, Object> state = new ConcurrentHashMap<>();

    @GetMapping("/state")
    public Map<String, Object> getState() {
        return state;
    }

    @PostMapping("/state")
    public Map<String, Object> putState(@RequestBody Map<String, Object> body) {
        Object key = body.get("key");
        Object value = body.get("value");

        if (key != null) {
            state.put(key.toString(), value);
        }

        return Map.of("saved", true, "key", key);
    }

    @GetMapping("/health")
    public Map<String, Object> health() {
        return Map.of("ok", true);
    }
}
