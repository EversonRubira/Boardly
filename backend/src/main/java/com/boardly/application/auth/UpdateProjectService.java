package com.boardly.application.auth;

import com.boardly.application.usecase.UpdateProjectUseCase;
import com.boardly.domain.model.Project;
import com.boardly.domain.repository.ProjectRepositoryPort;

// com.boardly.application.service.UpdateProjectService
public class UpdateProjectService implements UpdateProjectUseCase {
    private final ProjectRepositoryPort repo;
    public UpdateProjectService(ProjectRepositoryPort repo){ this.repo = repo; }

    @Override public Project update(String id, Project changes){
        Project p = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Project not found: " + id));
        if (changes.getTitle()!=null)       p.setTitle(changes.getTitle());
        if (changes.getDescription()!=null) p.setDescription(changes.getDescription());
        if (changes.getStartDate()!=null)   p.setStartDate(changes.getStartDate());
        if (changes.getEndDate()!=null)     p.setEndDate(changes.getEndDate());
        // tasks permanecem como estão (update parcial)
        return repo.save(p);
    }
}
