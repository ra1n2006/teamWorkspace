package com.example.backend.controller;


import com.example.backend.DTO.note.NoteResponse;
import com.example.backend.DTO.project.JoinProjectRequest;
import com.example.backend.DTO.note.NoteDTO;
import com.example.backend.DTO.note.NoteRequest;
import com.example.backend.DTO.project.ProjectDTO;
import com.example.backend.DTO.project.ProjectResponse;
import com.example.backend.DTO.user.UserDTO;
import com.example.backend.model.Note;
import com.example.backend.model.Project;
import com.example.backend.model.User;
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
                RequestMethod.OPTIONS,
                RequestMethod.PUT
        }
)
public class ProjectController {

    private final ProjectService projectService;
    private final NoteService noteService;

    public ProjectController(ProjectService projectService, ProjectRepository projectRepository, NoteService noteService) {
        this.projectService = projectService;
        this.noteService = noteService;
    }

    @GetMapping("/api/project/{id}")
    public ProjectResponse loadProject(@PathVariable Long id, Authentication authentication) {
        Set<NoteDTO> noteDTOs = projectService.getNotes(id);
        Set<UserDTO> userDTOs = projectService.getUsers(id);

        return new ProjectResponse(noteDTOs, userDTOs);
    }

    @PostMapping("/api/project/newNote/{id}")
    public NoteDTO newNote(@PathVariable Long id, @RequestBody NoteRequest noteReq, Authentication authentication) {
        return noteService.createNote(id, noteReq.getTitle());
    }

    @PutMapping("/api/project/join")
    public ProjectDTO joinProject(@RequestBody JoinProjectRequest request, Authentication authentication) {
        String login = authentication.getName();
        return projectService.joinProject(request.getToken(), login);
    }

    @PostMapping("/api/project/note/{id}/save")
    public NoteResponse saveNoteContent(@PathVariable Long id, @RequestBody NoteRequest noteReq, Authentication authentication) {
        String login = authentication.getName();
        String content = noteReq.getContent();
        NoteDTO note = noteService.updateNote(id, content);
        return new NoteResponse(note.getTitle(), note.getContent());
    }
}
