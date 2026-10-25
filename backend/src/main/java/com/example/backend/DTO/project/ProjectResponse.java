package com.example.backend.DTO.project;

import com.example.backend.DTO.note.NoteDTO;
import com.example.backend.DTO.user.UserDTO;

import java.util.Set;

public class ProjectResponse {
    private Set<UserDTO> users;
    private Set<NoteDTO> notes;

    public ProjectResponse(Set<NoteDTO> notes, Set<UserDTO> users) {
        this.notes = notes;
        this.users = users;
    }

    public Set<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDTO> users) {
        this.users = users;
    }

    public Set<NoteDTO> getNotes() {
        return notes;
    }

    public void setNotes(Set<NoteDTO> notes) {
        this.notes = notes;
    }
}
