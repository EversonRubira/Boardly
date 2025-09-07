package com.boardly.adapters.in.web.dto.project;

import com.boardly.adapters.in.web.dto.task.TaskResponse;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

// com.boardly.adapters.in.web.dto.ProjectDetailsResponse


public record DetailsProjectResponse(
        String id,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        List<TaskResponse> tasks
) {}

