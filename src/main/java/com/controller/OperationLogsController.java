package com.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.service.OperationLogsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/logs")
@Tag(name = "OperationLogs", description = "Operation logs management endpoints")
public class OperationLogsController {
    @Autowired
    OperationLogsService operationLogsService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }

    @GetMapping
    @Operation(summary = "Get all logs", description = "Get all the logs")
    public ResponseEntity<?> getAllLogs(@RequestParam(required = false) Integer idLab){
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
