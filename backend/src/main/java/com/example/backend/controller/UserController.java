package com.example.backend.controller;


import com.example.backend.DTO.project.ProjectDTO;
import com.example.backend.DTO.user.UserProjectRequest;
import com.example.backend.DTO.user.UserProjectResponse;
import com.example.backend.service.ProjectService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    public UserController(ProjectService projectService) {
        this.projectService = projectService;
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
        String title = request.getTitle();
        return projectService.createNew(login, title);
    }

}
