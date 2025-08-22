package com.boardly.adapters.in.web.dto.project;

import lombok.*;

import java.time.LocalDate;

// com.boardly.adapters.in.web.dto.ProjectUpdateRequest
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProjectRequest {
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
}
