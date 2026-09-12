package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Note {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Project project;

    private String title;

    private String content;

    public Note() {

    }

    public int getId() {
        return id;
    }

    public Note(Project projectID, String title) {
        this.project = projectID;
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Project getProjectID() {
        return project;
    }

    public void setProjectID(Project projectID) {
        this.project = projectID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
