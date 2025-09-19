package com.boardly.application.usecase;

import com.boardly.adapters.in.web.dto.task.TaskCreateRequest;
import com.boardly.adapters.in.web.dto.task.TaskResponse;

public interface CreateTaskUseCase {
    TaskResponse execute(TaskCreateRequest request);
}
