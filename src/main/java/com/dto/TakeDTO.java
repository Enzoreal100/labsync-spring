package com.dto;

import java.util.List;

import jakarta.validation.constraints.Min;

public class TakeDTO {
    @Min(value = 1, message = "ID deve ser um número inteiro e positivo")
    private int userId;

    private List<TakeItemsDTO> items;

    public TakeDTO() {
    }

    public TakeDTO(int userId, List<TakeItemsDTO> items) {
        this.userId = userId;
        this.items = items;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<TakeItemsDTO> getItems() {
        return items;
    }

    public void setItems(List<TakeItemsDTO> items) {
        this.items = items;
    }    
}
