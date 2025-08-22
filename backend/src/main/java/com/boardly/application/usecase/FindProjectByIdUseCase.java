package com.boardly.application.usecase;

import com.boardly.domain.model.Project;

// com.boardly.application.usecase.FindProjectByIdUseCase
public interface FindProjectByIdUseCase {
    Project find(String id);
}
