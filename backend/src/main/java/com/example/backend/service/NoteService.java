package com.example.backend.service;

import com.example.backend.DTO.note.NoteDTO;
import com.example.backend.model.Note;
import com.example.backend.model.Project;
import com.example.backend.repository.NoteRepository;
import com.example.backend.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
    private final ProjectRepository projectRepository;
    private final NoteRepository noteRepository;

    public NoteService(ProjectRepository projectRepository, NoteRepository noteRepository) {
        this.projectRepository = projectRepository;
        this.noteRepository = noteRepository;
    }

    public NoteDTO createNote(Long proj_id, String title) {
        Project project = projectRepository.findById(proj_id).orElseThrow();
        Note note = noteRepository.save(new Note(project, title));
        return new NoteDTO(title);
    }

    public NoteDTO updateNote(Long id, String content) {
        Note note = noteRepository.findById(id).orElseThrow();
        note.setContent(content);
        noteRepository.save(note);
        return new NoteDTO(note.getTitle(), note.getContent());
    }

    public NoteDTO getNote(Long id) {
        Note note = noteRepository.findById(id).orElseThrow();
        return new NoteDTO(note.getId(), note.getTitle(), note.getContent());
    }
}
