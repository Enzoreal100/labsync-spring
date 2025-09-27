package com.dto;

public class UserDTO {
    private int id;
    private int lab;
    private int position;
    private String name;
    private String cardCode;

    public UserDTO() {}

    public UserDTO(int id, String name, int position, int lab) {
        this.id = id;
        this.name = name;
        this.lab = lab;
        this.position = position;
    }

    public UserDTO(int id, int lab, int position, String name, String cardCode) {
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