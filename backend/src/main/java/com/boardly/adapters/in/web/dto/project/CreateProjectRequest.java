package com.boardly.adapters.in.web.dto.project;

import java.time.LocalDate;

public record CreateProjectRequest(
        @jakarta.validation.constraints.NotBlank String title,
        String description,
        java.time.LocalDate startDate,
        java.time.LocalDate endDate
) {}
