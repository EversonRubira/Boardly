package com.boardly.application.usecase.command;

import java.time.LocalDate;

public record CreateProjectCommand(
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate
) {}
