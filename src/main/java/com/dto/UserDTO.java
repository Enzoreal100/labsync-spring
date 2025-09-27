package com.dto;

import jakarta.validation.constraints.*;

public class UserDTO {

    @Min(value = 0, message = "ID deve ser um número inteiro e positivo")
    private Integer id;

    @Min(value = 0, message = "Lab deve ser um número inteiro e positivo")
    private Integer lab;

    @Min(value = 1, message = "Position deve ser entre 1 e 3")
    @Max(value = 3, message = "Position deve ser entre 1 e 3")
    private Integer position;

    @NotBlank(message = "Name não pode estar vazio")
    private String name;

    @Pattern(regexp = "^[A-Z0-9]{2}-[A-Z0-9]{2}-[A-Z0-9]{2}-[A-Z0-9]{2}-[A-Z0-9]{2}$", message = "Card code deve estar no formato XX-XX-XX-XX-XX")
    private String cardCode;

    public UserDTO() {}

    public UserDTO(int id, String name, int position, int lab) {
        this.id = id;
        this.name = name;
        this.lab = lab;
        this.position = position;
    }

    public UserDTO(int id, String name, int position, int lab, String cardCode) {
        this.id = id;
        this.lab = lab;
        this.position = position;
        this.name = name;
        this.cardCode = cardCode;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }
}