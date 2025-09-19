package com.boardly.application.auth;

import com.boardly.application.usecase.CreateProjectUseCase;
import com.boardly.domain.model.Project;
import com.boardly.application.ports.ProjectRepositoryPort;

public class CreateProjectService implements CreateProjectUseCase {
    private final ProjectRepositoryPort projectRepo;

    public CreateProjectService(ProjectRepositoryPort projectRepo) {
        this.projectRepo = projectRepo;
    }

    @Override
    public Output execute(Input in) {
        Project project = new Project(in.title(), in.description(), in.startDate(), in.endDate());
        Project saved = projectRepo.save(project);
        return new Output(saved.getId(), saved.getTitle(), saved.getDescription(), saved.getStartDate(), saved.getEndDate());
    }
}
