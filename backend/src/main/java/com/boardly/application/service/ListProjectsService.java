package com.boardly.application.service;

import java.util.List;
import java.util.stream.Collectors;

import com.boardly.application.usecase.ListProjectsUseCase;
import com.boardly.domain.model.Project;
import com.boardly.domain.repository.ProjectRepositoryPort;

public class ListProjectsService implements ListProjectsUseCase {
    private final ProjectRepositoryPort projectRepo;

    public ListProjectsService(ProjectRepositoryPort projectRepo) {
        this.projectRepo = projectRepo;
    }

    @Override
    public List<Output> execute() {
        List<Project> all = projectRepo.findAll();
        return all.stream()
                .map(p -> new Output(p.getId(), p.getTitle(), p.getDescription(), p.getStartDate(), p.getEndDate()))
                .collect(Collectors.toList());
    }
}
