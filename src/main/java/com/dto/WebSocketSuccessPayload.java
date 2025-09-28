package com.dto;

public class WebSocketSuccessPayload {
    private String status = "SUCCESS";
    private UserDTO data;

    public WebSocketSuccessPayload(UserDTO data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public UserDTO getData() {
        return data;
    }
}
