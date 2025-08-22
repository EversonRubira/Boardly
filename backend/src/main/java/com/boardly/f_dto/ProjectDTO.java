package com.boardly.f_dto;

import com.boardly.e_model.Task;
import java.time.LocalDate;
import java.util.List;

public record ProjectDTO(
        String id,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        List<Task> tasks
) {}
