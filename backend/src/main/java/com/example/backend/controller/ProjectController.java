package com.example.backend.controller;


import com.example.backend.DTO.NoteDTO;
import com.example.backend.DTO.NoteRequest;
import com.example.backend.model.Note;
import com.example.backend.model.Project;
import com.example.backend.repository.ProjectRepository;
import com.example.backend.service.NoteService;
import com.example.backend.service.ProjectService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(
        origins = "http://localhost:5173",
        methods = {
                RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.OPTIONS
        }
)
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectRepository projectRepository;
    private final NoteService noteService;

    public ProjectController(ProjectService projectService, ProjectRepository projectRepository, NoteService noteService) {
        this.projectService = projectService;
        this.projectRepository = projectRepository;
        this.noteService = noteService;
    }

    @GetMapping("/api/project/{id}/notes")
    public Set<NoteDTO> getNotes(
            @PathVariable Long id,
            Authentication authentication
    ) {
        String login = authentication.getName();

        Project project = projectRepository.findById(id)
                .orElseThrow();

        Set<Note> notes = project.getNotes();

        return notes.stream()
                .map(note -> new NoteDTO(
                        note.getId(),
                        note.getTitle(),
                        note.getContent()
                ))
                .collect(Collectors.toSet());
    }

    @PostMapping("/api/project/newNote/{id}")
    public NoteDTO newNote(@PathVariable Long id, @RequestBody NoteRequest noteReq, Authentication authentication) {
        String login = authentication.getName();
        return noteService.createNote(id, noteReq.getTitle());
    }
}
