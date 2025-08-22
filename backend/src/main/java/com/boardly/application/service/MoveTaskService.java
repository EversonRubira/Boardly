package com.boardly.application.service;

import com.boardly.adapters.in.web.dto.task.TaskMoveRequest;
import com.boardly.adapters.in.web.dto.task.TaskResponse;
import com.boardly.application.usecase.MoveTaskUseCase;
import com.boardly.domain.common.Status;
import com.boardly.domain.model.Project;
import com.boardly.domain.model.Task;
import com.boardly.domain.repository.ProjectRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class MoveTaskService implements MoveTaskUseCase {

    private final ProjectRepositoryPort projectRepository;

    public MoveTaskService(ProjectRepositoryPort projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public TaskResponse execute(String taskId, TaskMoveRequest request) {
        // Percorre todos os projetos e busca a task com o ID informado
        for (Project project : projectRepository.findAll()) {
            for (Task task : project.getTasks()) {
                if (task.getId().equals(taskId)) {
                    // Atualiza o status
                    task.setStatus(Status.valueOf(request.getNewStatus()));
                    // Salva o projeto com a task atualizada
                    projectRepository.save(project);

                    return new TaskResponse(
                            task.getId(),
                            task.getTitle(),
                            task.getAssignee(),
                            task.getStatus().name()
                    );
                }
            }
        }

        throw new RuntimeException("Tarefa não encontrada com ID: " + taskId);
    }
}
