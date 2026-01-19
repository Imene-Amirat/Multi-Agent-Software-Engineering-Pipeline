package com.example.gl4ia.observability.controller;

import com.example.gl4ia.observability.dto.EventRequest;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping
public class ObservabilityController {

    private final List<Map<String, Object>> events = new CopyOnWriteArrayList<>();

    @GetMapping("/health")
    public Map<String, Object> health() {
        return Map.of("ok", true);
    }

    @PostMapping("/event")
    public Map<String, Object> pushEvent(@RequestBody EventRequest e) {
        Map<String, Object> event = new HashMap<>();
        event.put("timestamp", Instant.now().toString());
        event.put("service", e.service());
        event.put("step", e.step());
        event.put("payload", e.payload() == null ? Map.of() : e.payload());

        events.add(event);
        return Map.of("accepted", true, "count", events.size());
    }

    @GetMapping("/events")
    public List<Map<String, Object>> allEvents() {
        return events;
    }
}
