package com.boardly.adapters.in.web.controller;

import com.boardly.adapters.in.web.dto.project.DetailsProjectResponse;
import com.boardly.application.usecase.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.boardly.domain.model.Task;
import com.boardly.adapters.in.web.dto.task.TaskResponse;

import com.boardly.adapters.in.web.dto.project.CreateProjectRequest;
import com.boardly.adapters.in.web.dto.project.ListProjectResponse;
import com.boardly.domain.model.Project;
import com.boardly.adapters.in.web.dto.project.UpdateProjectRequest;


@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final CreateProjectUseCase createProject;
    private final ListProjectsUseCase listProjects;
    private final FindProjectByIdUseCase findProjectByIdUseCase;
    private final UpdateProjectUseCase updateProjectUseCase;
    private final DeleteProjectUseCase deleteProjectUseCase;

    public ProjectController(CreateProjectUseCase createProject,
                             ListProjectsUseCase listProjects,
                             FindProjectByIdUseCase findProjectByIdUseCase,
                             UpdateProjectUseCase updateProjectUseCase,
                             DeleteProjectUseCase deleteProjectUseCase) {
        this.createProject = createProject;
        this.listProjects = listProjects;
        this.findProjectByIdUseCase = findProjectByIdUseCase;
        this.updateProjectUseCase = updateProjectUseCase;
        this.deleteProjectUseCase = deleteProjectUseCase;
    }

    @PostMapping
    public ResponseEntity<ListProjectResponse> create(@RequestBody CreateProjectRequest req) {
        var out = createProject.execute(
                new CreateProjectUseCase.Input(req.title(), req.description(), req.startDate(), req.endDate())
        );
        return ResponseEntity.ok(
                new ListProjectResponse(out.id(), out.title(), req.description(), req.startDate(), req.endDate()));
    }

    @GetMapping
    public ResponseEntity<List<ListProjectResponse>> list() {
        var out = listProjects.execute();
        var resp = out.stream()
                .map(o -> new ListProjectResponse(o.id(), o.title(), o.description(), o.startDate(), o.endDate()))
                .toList();
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailsProjectResponse> getById(@PathVariable String id){
        Project p = findProjectByIdUseCase.find(id);
        return ResponseEntity.ok(toDetailsResponse(p));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetailsProjectResponse> update(@PathVariable String id,
                                                         @RequestBody UpdateProjectRequest req){
        Project changes = Project.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .startDate(req.getStartDate())
                .endDate(req.getEndDate())
                .build();

        Project updated = updateProjectUseCase.update(id, changes);
        return ResponseEntity.ok(toDetailsResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        deleteProjectUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    private DetailsProjectResponse toDetailsResponse(Project p){
        List<TaskResponse> tasks = p.getTasks()==null? List.of() :
                p.getTasks().stream().map(this::toTaskResponse).toList();
        return DetailsProjectResponse.builder()
                .id(p.getId())
                .title(p.getTitle())
                .description(p.getDescription())
                .startDate(p.getStartDate())
                .endDate(p.getEndDate())
                .tasks(tasks)
                .build();
    }

    private TaskResponse toTaskResponse(Task t){
        return TaskResponse.builder()
                .id(t.getId())
                .title(t.getTitle())
                .assignee(t.getAssignee())
                .status(t.getStatus().name())
                .build();
    }
}

