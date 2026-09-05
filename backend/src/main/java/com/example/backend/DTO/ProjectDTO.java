package com.example.backend.DTO;

public class ProjectDTO {

    private int id;
    private String name;

    public ProjectDTO(int id, String title) {
        this.id = id;
        this.name = title;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}