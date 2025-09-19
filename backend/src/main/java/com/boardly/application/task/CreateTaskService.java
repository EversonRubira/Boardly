package com.boardly.application.auth;

import com.boardly.adapters.in.web.dto.task.TaskCreateRequest;
import com.boardly.adapters.in.web.dto.task.TaskResponse;
import com.boardly.application.usecase.CreateTaskUseCase;
import com.boardly.domain.common.Status;
import com.boardly.domain.model.Project;
import com.boardly.domain.model.Task;
import com.boardly.application.ports.ProjectRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateTaskService implements CreateTaskUseCase {

    private final ProjectRepositoryPort projectRepository;

    public CreateTaskService(ProjectRepositoryPort projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public TaskResponse execute(TaskCreateRequest request) {
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        Task task = new Task(request.getTitle());
        task.setId(UUID.randomUUID().toString());
        task.setAssignee(request.getAssignee());
        task.setStatus(Status.TO_DO);

        project.getTasks().add(task);
        projectRepository.save(project);

        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getAssignee(),
                task.getStatus().name()
        );
    }
}
