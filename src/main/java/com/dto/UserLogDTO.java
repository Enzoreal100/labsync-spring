package com.dto;

import jakarta.validation.constraints.*;

public class UserLogDTO {

    @Min(value = 0, message = "ID deve ser um número inteiro e positivo")
    private Integer id;

    @Min(value = 0, message = "Lab deve ser um número inteiro e positivo")
    private Integer lab;

    private String position;

    @NotBlank(message = "Name não pode estar vazio")
    private String name;

    @Pattern(regexp = "^[A-Z0-9]{2}-[A-Z0-9]{2}-[A-Z0-9]{2}-[A-Z0-9]{2}-[A-Z0-9]{2}$", message = "Card code deve estar no formato XX-XX-XX-XX-XX")
    private String cardCode;

    public UserLogDTO() {}

    public UserLogDTO(int id, String name, String position, int lab) {
        this.id = id;
        this.name = name;
        this.lab = lab;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLab() {
        return lab;
    }

    public void setLab(int lab) {
        this.lab = lab;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}