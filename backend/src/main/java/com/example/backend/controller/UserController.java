package com.example.backend.controller;


import com.example.backend.DTO.ProjectDTO;
import com.example.backend.DTO.UserProjectRequest;
import com.example.backend.DTO.UserProjectResponse;
import com.example.backend.model.Project;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.example.backend.model.User;

import java.util.Set;

@RestController
@CrossOrigin(
        origins = "http://localhost:5173",
        methods = {
                RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.OPTIONS
        }
)
public class UserController {

    private final ProjectService projectService;
    private final UserRepository userRepository;

    public UserController(ProjectService projectService, UserRepository userRepository) {
        this.projectService = projectService;
        this.userRepository = userRepository;
    }

    @GetMapping("/api/main")
    public UserProjectResponse getMainData(Authentication authentication) {
        String login = authentication.getName();
        return projectService.getProjects(login);
    }

    @PostMapping("/api/new")
    public ProjectDTO createNewProject(
            @RequestBody UserProjectRequest request,
            Authentication authentication
    ) {
        String login = authentication.getName();

        User user = userRepository.findByLogin(login)
                .orElseThrow();

        Project project = new Project(request.getTitle(), user);

        return projectService.createNew(project);
    }

}
