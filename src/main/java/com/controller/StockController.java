package com.controller;

import com.dto.StockDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.service.StockService;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
@Tag(name = "Stock", description = "Stock management endpoints")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/{labId}")
    public ResponseEntity<?> getAllItems(@PathVariable int labId) {
        if (labId <= 0) {
            return ResponseEntity.badRequest().body("labId deve ser um número inteiro maior que 0");
        }
        return ResponseEntity.ok(stockService.getAllItems(labId));
    }
}
