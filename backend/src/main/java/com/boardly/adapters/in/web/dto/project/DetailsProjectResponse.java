package com.boardly.adapters.in.web.dto.project;

import com.boardly.adapters.in.web.dto.task.TaskResponse;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

// com.boardly.adapters.in.web.dto.ProjectDetailsResponse
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailsProjectResponse {
    private String id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<TaskResponse> tasks;
}
