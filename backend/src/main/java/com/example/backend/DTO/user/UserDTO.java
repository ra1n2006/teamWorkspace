package com.example.backend.DTO.user;

public class UserDTO {

    private Long id;
    private String login;

    public UserDTO(Long id, String login) {
        this.id = id;
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
}
