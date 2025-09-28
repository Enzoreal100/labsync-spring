package com.dto;

public class WebSocketSuccessPayload {
    private String status = "SUCCESS";
    private Object data;

    public WebSocketSuccessPayload(Object data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }
}
