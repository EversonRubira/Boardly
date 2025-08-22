package com.boardly.application.usecase;

import com.boardly.adapters.in.web.dto.task.TaskMoveRequest;
import com.boardly.adapters.in.web.dto.task.TaskResponse;

public interface MoveTaskUseCase {
    TaskResponse execute(String taskId, TaskMoveRequest request);
}
