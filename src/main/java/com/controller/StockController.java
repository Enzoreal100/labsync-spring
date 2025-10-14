package com.controller;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dto.TakeDTO;
import com.service.StockService;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/stock")
@Tag(name = "Stock", description = "Stock management endpoints")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/{labId}")
    @Operation(summary = "Get items available", description = "Get all the items available on the Lab")
    public ResponseEntity<?> getAllItems(@PathVariable int labId, @RequestHeader("Authorization") String token) {
        if (labId <= 0) {
            return ResponseEntity.badRequest().body("labId deve ser um número inteiro maior que 0");
        }
        return ResponseEntity.ok(stockService.getAllItems(labId));
    }

    @PostMapping("/take")
    @Operation(summary = "take items from stock", description = "take a batch of items from the stock")
    public ResponseEntity<?> takeItemsFromStock(@org.springframework.web.bind.annotation.RequestBody @Valid TakeDTO takeData, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(stockService.takeItemsFromStock(takeData));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
