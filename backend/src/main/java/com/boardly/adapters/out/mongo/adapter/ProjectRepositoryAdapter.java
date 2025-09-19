package com.boardly.adapters.out.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.boardly.adapters.out.mongo.repository.SpringDataProjectRepository;
import com.boardly.adapters.out.mongo.document.ProjectDocument;
import com.boardly.adapters.out.mongo.mapper.ProjectMongoMapper;
import com.boardly.domain.model.Project;
import com.boardly.application.ports.ProjectRepositoryPort;

public class ProjectRepositoryAdapter implements ProjectRepositoryPort {
    private final SpringDataProjectRepository repo;
    private final ProjectMongoMapper mapper;

    public ProjectRepositoryAdapter(SpringDataProjectRepository repo, ProjectMongoMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public Project save(Project project) {
        ProjectDocument saved = repo.save(mapper.toDocument(project));
        return mapper.toDomain(saved);
    }

    @Override
    public List<Project> findAll() {
        return repo.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Project> findById(String id) {
        return repo.findById(id).map(mapper::toDomain);
    }

    @Override
    public void deleteById(String id) {
        repo.deleteById(id);
    }
}
