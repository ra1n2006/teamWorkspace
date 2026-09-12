package com.example.backend.service;

import com.example.backend.DTO.NoteDTO;
import com.example.backend.DTO.ProjectDTO;
import com.example.backend.DTO.UserProjectResponse;
import com.example.backend.model.Note;
import com.example.backend.model.Project;
import com.example.backend.model.User;
import com.example.backend.repository.ProjectRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(
            UserRepository userRepository,
            ProjectRepository projectRepository
    ) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    public ProjectDTO createNew(Project project) {
        Project savedProject = projectRepository.save(project);

        return new ProjectDTO(
                savedProject.getId(),
                savedProject.getName()
        );
    }

    public UserProjectResponse getProjects(String login) {
        User user = userRepository.findByLogin(login).orElseThrow();

        Set<ProjectDTO> projects = user.getProjects()
                .stream()
                .map(project -> new ProjectDTO(
                        project.getId(),
                        project.getName()
                ))
                .collect(Collectors.toSet());

        return new UserProjectResponse(projects);
    }

//    public Set<NoteDTO> getNotes(Long id, String login) {
//        Project project = projectRepository.findById(id).orElseThrow();
//
//        Set<Note> notes = project.getNotes();
//
//        Set<NoteDTO> notesDTO = notes.stream()
//                .map(note -> new NoteDTO(
//                        note.getId(),
//                        note.getTitle(),
//                        note.getContent()
//                ))
//                .collect(Collectors.toSet());
//
//        return notesDTO;
//    }
}
