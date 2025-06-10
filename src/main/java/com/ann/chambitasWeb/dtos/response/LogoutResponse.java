package com.ann.chambitasWeb.dtos.response;

public class LogoutResponse {

    private String message;

    // Constructor
    public LogoutResponse(String message) {
        this.message = message;
    }

    // Getter y Setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}