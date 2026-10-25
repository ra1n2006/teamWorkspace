package com.example.backend.DTO.auth;

import com.example.backend.DTO.user.UserDTO;

public class AuthResponse {
    private int status;
    private String message;
    private UserDTO user;
    private String token;

    public AuthResponse(int status, String message, UserDTO user, String token) {
        this.status = status;
        this.message = message;
        this.user = user;
        this.token = token;
    }

    public AuthResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
