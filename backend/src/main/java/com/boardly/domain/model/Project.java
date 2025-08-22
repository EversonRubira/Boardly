package com.boardly.domain.model;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor (force = true)
@AllArgsConstructor
@Builder
public class Project {
    private String id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private final List<Task> tasks = new ArrayList<>();

    public Project(String title, String description, LocalDate startDate, LocalDate endDate) {
        if (title == null || title.isBlank()) throw new IllegalArgumentException("title is required");
        if (startDate != null && endDate != null && endDate.isBefore(startDate))
            throw new IllegalArgumentException("endDate must be after startDate");
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public List<Task> getTasks() { return tasks; }
}
