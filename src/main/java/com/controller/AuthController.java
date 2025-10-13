package com.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.dto.auth.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.dto.auth.LoginDTO;
import com.entity.User;
import com.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "Auth API")
public class AuthController {
    
    @Autowired
    private AuthService authService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid LoginDTO loginDTO) {

        Optional<User> user = authService.validateUser(loginDTO);
        String passwordHash = user.isPresent() ? user.get().getPassword_hash() : authService.getFakeHash();
        
        boolean isValidPassword = authService.verifyPassword(loginDTO.getPassword(), passwordHash);
        
        if (user.isEmpty() || !isValidPassword) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Usuário ou senha incorretos");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
        
        String token = authService.generateToken(user.get().getId(), user.get().getPosition(), user.get().getLab());
        
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/refresh")
    @Operation(summary = "refresh token", description = "refresh the auth token")
    public ResponseEntity<String> refresh(@AuthenticationPrincipal TokenDTO token){
        if (token ==  null){
            System.out.println("CARALHO");
        }
        String mockResp = "Sub: " + token.getSub() + "Position: " + token.getPositionId() + "Lab: " + token.getLabId() + "iat: " + token.getIat() + "Exp: " + token.getExp();
        return ResponseEntity.ok(mockResp);
    }
}
