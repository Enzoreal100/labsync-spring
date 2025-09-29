package com.dto;

public class StockDTO {
    private int id;
    private String eanCode;
    private String name;
    private String imageUrl;
    private int quantity;
    private int minQuantity;
    private int labId;

    public StockDTO() {}

    public StockDTO(int id, String eanCode, String name, String imageUrl, int quantity, int minQuantity, int labId) {
        this.id = id;
        this.eanCode = eanCode;
        this.name = name;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
        this.minQuantity = minQuantity;
        this.labId = labId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEanCode() {
        return eanCode;
    }

    public void setEanCode(String eanCode) {
        this.eanCode = eanCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }
}