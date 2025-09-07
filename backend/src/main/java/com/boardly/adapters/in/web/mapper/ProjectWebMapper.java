package com.boardly.adapters.in.web.mapper;

import com.boardly.application.usecase.command.CreateProjectCommand;
import com.boardly.adapters.in.web.dto.project.CreateProjectRequest;
import com.boardly.adapters.in.web.dto.project.DetailsProjectResponse;
import com.boardly.adapters.in.web.dto.task.TaskResponse;
import com.boardly.domain.model.Project;

import java.util.List;

public final class ProjectWebMapper {
    private ProjectWebMapper() {}

    public static CreateProjectCommand toCommand(CreateProjectRequest req) {
        return new CreateProjectCommand(
                req.title(),
                req.description(),
                req.startDate(),
                req.endDate()
        );
    }

    public static DetailsProjectResponse toDetails(Project p) {
        List<TaskResponse> tasks = (p.getTasks() == null) ? List.of()
                : p.getTasks().stream()
                .map(t -> new TaskResponse(
                        t.getId(),
                        t.getTitle(),
                        t.getAssignee(),
                        t.getStatus().name()
                ))
                .toList();

        return new DetailsProjectResponse(
                p.getId(),
                p.getTitle(),
                p.getDescription(),
                p.getStartDate(),
                p.getEndDate(),
                tasks
        );
    }
}
