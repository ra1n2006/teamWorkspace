package com.example.backend.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="project")
public class Project {

    @Id
    @GeneratedValue
    private int id;

    public Project(String name, User user) {
        this.name = name;
        this.users.add(user);
        user.getProjects().add(this);
    }

    @ManyToMany(mappedBy = "projects")
    private Set<User> users = new HashSet<User>();

    private String name;

    @OneToMany(mappedBy = "project")
    private Set<Note> notes = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project() {

    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
