package com.dto;

import jakarta.validation.constraints.Min;

public class TakeItemsDTO {

    @Min(value = 1, message = "ID deve ser um número inteiro e positivo")
    private int id;

    @Min(value = 1, message = "Take quantity deve ser um número inteiro e positivo")
    private int takeQuantity;

    @Min(value = 1, message = "Lab ID deve ser um número inteiro e positivo")
    private int labId;

    public TakeItemsDTO() {
    }

    public TakeItemsDTO(int id, int takeQuantity, int labId) {
        this.id = id;
        this.takeQuantity = takeQuantity;
        this.labId = labId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTakeQuantity() {
        return takeQuantity;
    }

    public void setTakeQuantity(int takeQuantity) {
        this.takeQuantity = takeQuantity;
    }

    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }    
}
