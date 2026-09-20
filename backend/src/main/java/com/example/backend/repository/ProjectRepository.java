package com.example.backend.repository;

import com.example.backend.model.Note;
import com.example.backend.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findById(Long id);

    Optional<Project> findByInviteLink(String inviteLink);
}
