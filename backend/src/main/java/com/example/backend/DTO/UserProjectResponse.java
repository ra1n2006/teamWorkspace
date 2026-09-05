package com.example.backend.DTO;

import com.example.backend.model.Project;

import java.util.Set;

public class UserProjectResponse {
    private Set<ProjectDTO> projects;
    public UserProjectResponse(Set<ProjectDTO> projects) {
        this.projects = projects;
    }

    public Set<ProjectDTO> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectDTO> projects) {
        this.projects = projects;
    }
}
