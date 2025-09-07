package com.boardly.application.auth;

import com.boardly.application.usecase.FindProjectByIdUseCase;
import com.boardly.domain.model.Project;
import com.boardly.domain.repository.ProjectRepositoryPort;

// com.boardly.application.service.FindProjectByIdService
public class FindProjectByIdService implements FindProjectByIdUseCase {
    private final ProjectRepositoryPort repo;
    public FindProjectByIdService(ProjectRepositoryPort repo){ this.repo = repo; }

    @Override public Project find(String id){
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Project not found: " + id));
    }
}