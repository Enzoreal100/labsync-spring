package com.controller;

import com.dto.HealthDTO;
import com.service.HealthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
@Tag(name = "Health", description = "Health check endpoints")
public class HealthController {

    @Autowired
    private HealthService healthService;

    @GetMapping
    @Operation(summary = "Check application health", description = "Returns the current health status of the application")
    public ResponseEntity<HealthDTO> health() {
        try {
            HealthDTO healthStatus = healthService.getHealthStatus();
            return ResponseEntity.ok(healthStatus);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}