package com.boardly.b_service;

import com.boardly.f_dto.ProjectDTO;
import com.boardly.g_mapper.ProjectMapper;
import com.boardly.e_model.Project;
import com.boardly.d_repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository repository;

    @Autowired
    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public List<ProjectDTO> getAllProjects() {
        return repository.findAll().stream()
                .map(ProjectMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProjectDTO> getProjectById(String id) {
        return repository.findById(id)
                .map(ProjectMapper::toDTO);
    }

    public ProjectDTO createProject(ProjectDTO dto) {
        Project project = ProjectMapper.toEntity(dto);
        Project saved = repository.save(project);
        return ProjectMapper.toDTO(saved);
    }

    public ProjectDTO updateProject(String id, ProjectDTO dto) {
        return repository.findById(id).map(project -> {
            // atualiza os campos
            project.setTitle(dto.title());
            project.setDescription(dto.description());
            project.setStartDate(dto.startDate());
            project.setEndDate(dto.endDate());
            project.setTasks(dto.tasks());
            // apos atualizado salva no banco
            Project update = repository.save(project);
            return ProjectMapper.toDTO(update);
        }).orElseThrow(() -> new RuntimeException("Project not found" + id));
    }

    public void deleteProject(String id) {
        repository.deleteById(id);

    }
}