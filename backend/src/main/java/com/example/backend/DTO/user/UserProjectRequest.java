package com.example.backend.DTO;

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
