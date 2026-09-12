package com.example.backend.DTO;

public class NoteRequest {

    private String title;

    public NoteRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
