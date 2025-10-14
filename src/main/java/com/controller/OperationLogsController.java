package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.AuthService;
import com.service.OperationLogsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/logs")
@Tag(name = "OperationLogs", description = "Operation logs management endpoints")
public class OperationLogsController {
    @Autowired
    OperationLogsService operationLogsService;

    @Autowired
    AuthService authService;


    @GetMapping
    @Operation(summary = "Get all logs", description = "Get all the logs")
    public ResponseEntity<?> getAllLogs(@RequestParam(required = false) Integer idLab, @RequestAttribute("jwtToken") String token){
        int positionId = authService.extractPositionId(token);
        if (positionId > 2){
            return ResponseEntity.notFound().build();
        }
        if (idLab == null){
            return ResponseEntity.ok(operationLogsService.findAll());
        }
        else{ 
            if (idLab <= 0) {
                return ResponseEntity.badRequest().body("labId deve ser um número inteiro maior que 0");
            }
            return ResponseEntity.ok(operationLogsService.findByIdLab(idLab));
        }
    }
}
