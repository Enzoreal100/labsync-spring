package com.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoginDTO {
    
    @NotNull(message = "ID é obrigatório")
    private Integer id;

    @NotBlank(message = "Senha é obrigatória")
    private String password;

    public LoginDTO() {}

    public LoginDTO(Integer id, String password) {
        this.id = id;
        this.password = password;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}
