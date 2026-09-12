package com.example.backend.repository;

import com.example.backend.model.Project;
import com.example.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

    Optional<Set<Project>> findProjectByLogin(String login);

    Optional<Project> findProjectById(Long id);
}