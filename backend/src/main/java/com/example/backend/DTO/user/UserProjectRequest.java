package com.example.backend.DTO.user;

public class UserProjectRequest {
    private String title;

    public UserProjectRequest(String title) {
        this.title = title;
    }

    public UserProjectRequest() {
    }

    public String getTitle() {
        return title;
    }
}
