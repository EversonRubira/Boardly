package com.boardly.domain.repository;

import java.util.List;
import java.util.Optional;

import com.boardly.domain.model.Project;

public interface ProjectRepositoryPort {
    Project save(Project project);
    List<Project> findAll();
    Optional<Project> findById(String id);   // já sugerido
    void deleteById(String id);              // novo
}
