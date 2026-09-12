package com.example.backend.DTO;

public class NoteDTO {

    private int id;
    private String title;
    private String content;

    public NoteDTO(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public NoteDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public NoteDTO(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
