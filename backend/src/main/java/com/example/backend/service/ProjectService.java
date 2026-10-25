package com.example.backend.service;

import com.example.backend.DTO.note.NoteDTO;
import com.example.backend.DTO.project.ProjectDTO;
import com.example.backend.DTO.user.UserDTO;
import com.example.backend.DTO.user.UserProjectResponse;
import com.example.backend.model.Note;
import com.example.backend.model.Project;
import com.example.backend.model.User;
import com.example.backend.repository.ProjectRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final RedisService redisService;

    @Autowired
    public ProjectService(
            UserRepository userRepository,
            ProjectRepository projectRepository,
            RedisService redisService
    ) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.redisService = redisService;
    }

    public ProjectDTO createNew(String login, String title) {
        User user = userRepository.findByLogin(login)
                .orElseThrow();

        Project project = new Project(title, user);
        project.setInviteLink(UUID.randomUUID().toString());
        Project savedProject = projectRepository.save(project);
        redisService.saveInvite(
                savedProject.getInviteLink(),
                savedProject.getId()
        );
        return new ProjectDTO(
                savedProject.getId(),
                savedProject.getName(),
                savedProject.getInviteLink()
        );
    }

    public UserProjectResponse getProjects(String login) {
        User user = userRepository.findByLogin(login).orElseThrow();

        Set<ProjectDTO> projects = user.getProjects()
                .stream()
                .map(project -> new ProjectDTO(
                        project.getId(),
                        project.getName(),
                        project.getInviteLink()
                ))
                .collect(Collectors.toSet());

        return new UserProjectResponse(projects);
    }

    public ProjectDTO joinProject(String token, String login) {

        User user = userRepository.findByLogin(login)
                .orElseThrow();
        Integer projectId = redisService.getProjectId(token);
        if (projectId == null) {
            throw new RuntimeException("Invalid invite token");
        }
        Project project = projectRepository.findByInviteLink(token)
                .orElseThrow();

        user.getProjects().add(project);

        userRepository.save(user);

        return new ProjectDTO(project.getId(), project.getName(), project.getInviteLink());
    }

//    public Set<UserDTO> getUsers(Long projectId) {
//        Set<User> projectUsers = userRepository.findByProjectId(projectId).orElseThrow();
//        Set<UserDTO> users = projectUsers.stream().map(user -> new UserDTO(
//                user.getId(),
//                user.getLogin()
//        )).collect(Collectors.toSet());
//        return users;
//    }

    public Set<NoteDTO> getNotes(Long id) {
        Project project = projectRepository.findById(id).orElseThrow();

        Set<Note> notes = project.getNotes();

        Set<NoteDTO> notesDTO = notes.stream()
                .map(note -> new NoteDTO(
                        note.getId(),
                        note.getTitle(),
                        note.getContent()
                ))
                .collect(Collectors.toSet());

        return notesDTO;
    }

    public Set<UserDTO> getUsers(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow();

        Set<User> projectUsers = project.getUsers();

        Set<UserDTO> userDTOS = projectUsers.stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getLogin()
                )).collect(Collectors.toSet());

        return userDTOS;
    }
}
