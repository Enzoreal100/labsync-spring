package com.dto;

public class WebSocketErrorPayload {
    private String status = "ERROR";
    private String message;

    public WebSocketErrorPayload(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
