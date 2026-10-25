package com.example.backend.DTO;

public class JoinProjectRequest {
    private String token;

    public JoinProjectRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
