package com.boardly.application.usecase;

import com.boardly.domain.model.Project;

// com.boardly.application.usecase.UpdateProjectUseCase
public interface UpdateProjectUseCase {
    Project update(String id, Project changes);
}
