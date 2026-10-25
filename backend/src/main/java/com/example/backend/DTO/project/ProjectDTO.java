package com.example.backend.DTO.project;

public class ProjectDTO {

    private int id;
    private String name;
    private String inviteLink;

    public ProjectDTO(int id, String title, String inviteLink) {
        this.id = id;
        this.name = title;
        this.inviteLink = inviteLink;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInviteLink() {
        return inviteLink;
    }

    public void setInviteLink(String inviteLink) {
        this.inviteLink = inviteLink;
    }
}