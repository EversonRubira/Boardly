package com.boardly.application.auth;

import com.boardly.application.usecase.DeleteProjectUseCase;
import com.boardly.application.ports.ProjectRepositoryPort;

// com.boardly.application.service.DeleteProjectService
public class DeleteProjectService implements DeleteProjectUseCase {
    private final ProjectRepositoryPort repo;
    public DeleteProjectService(ProjectRepositoryPort repo){ this.repo = repo; }
    @Override public void delete(String id){ repo.deleteById(id); }
}
