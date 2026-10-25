package com.example.backend.DTO.note;

public class NoteResponse {
    private int id;
    private String title;
    private String content;
    private String authorName;

    public NoteResponse(int id, String title, String content, String authorName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorName = authorName;
    }

    public NoteResponse(String title, String content) {
        this.title = title;
        this.content = content;
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

    public String getAuthorName() {
        return authorName;
    }
}
