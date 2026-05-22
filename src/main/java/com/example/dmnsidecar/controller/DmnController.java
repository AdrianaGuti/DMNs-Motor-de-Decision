package com.example.dmnsidecar.controller;

import com.example.dmnsidecar.model.EvaluateRequest;
import com.example.dmnsidecar.model.EvaluateResponse;
import com.example.dmnsidecar.service.DmnService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DmnController {

    private final DmnService dmnService;

    public DmnController(DmnService dmnService) {
        this.dmnService = dmnService;
    }

    @PostMapping("/evaluate")
    public ResponseEntity<EvaluateResponse> evaluate(@RequestBody EvaluateRequest request) {
        EvaluateResponse response = dmnService.evaluate(request);
        int status = response.success() ? 200 : 422;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("status", "UP"));
    }
}
