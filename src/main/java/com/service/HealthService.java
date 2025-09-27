package com.service;

import com.dto.HealthDTO;
import org.springframework.stereotype.Service;

@Service
public class HealthService {

    public HealthDTO getHealthStatus() {
        return new HealthDTO("UP", "Application is running successfully");
    }
}