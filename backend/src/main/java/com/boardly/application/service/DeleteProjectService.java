package com.boardly.application.service;

import com.boardly.application.usecase.DeleteProjectUseCase;
import com.boardly.domain.repository.ProjectRepositoryPort;

// com.boardly.application.service.DeleteProjectService
public class DeleteProjectService implements DeleteProjectUseCase {
    private final ProjectRepositoryPort repo;
    public DeleteProjectService(ProjectRepositoryPort repo){ this.repo = repo; }
    @Override public void delete(String id){ repo.deleteById(id); }
}
