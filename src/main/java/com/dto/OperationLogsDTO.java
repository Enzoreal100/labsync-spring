package com.dto;

import java.util.ArrayList;

public class OperationLogsDTO {
    private int id;
    private UserLogDTO user;
    private String operationType;
    private ArrayList<ItemsLog> item;

    public OperationLogsDTO() {
    }

    public OperationLogsDTO(int id, UserLogDTO user, String operationType, ArrayList<ItemsLog> item) {
        this.id = id;
        this.user = user;
        this.operationType = operationType;
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserLogDTO getUser() {
        return user;
    }

    public void setUser(UserLogDTO user) {
        this.user = user;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public ArrayList<ItemsLog> getItem() {
        return item;
    }

    public void setItem(ArrayList<ItemsLog> item) {
        this.item = item;
    }
}