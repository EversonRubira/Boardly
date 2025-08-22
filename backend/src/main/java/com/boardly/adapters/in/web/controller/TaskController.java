package com.boardly.adapters.in.web.controller;


import com.boardly.adapters.in.web.dto.task.TaskCreateRequest;
import com.boardly.application.usecase.CreateTaskUseCase;
import com.boardly.application.usecase.MoveTaskUseCase;
import com.boardly.adapters.in.web.dto.task.TaskMoveRequest;
import com.boardly.adapters.in.web.dto.task.TaskResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final MoveTaskUseCase moveTaskUseCase;

    public TaskController(CreateTaskUseCase createTaskUseCase,
                          MoveTaskUseCase moveTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
        this.moveTaskUseCase = moveTaskUseCase;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskCreateRequest request) {
        TaskResponse created = createTaskUseCase.execute(request);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}/move")
    public ResponseEntity<TaskResponse> moveTask(@PathVariable String id,
                                                 @RequestBody TaskMoveRequest request) {
        TaskResponse updated = moveTaskUseCase.execute(id, request);
        return ResponseEntity.ok(updated);
    }
}
