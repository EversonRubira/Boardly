package com.boardly.adapters.out.mongo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.List;

@Document("projects")
public class ProjectDocument {
    @Id private String id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<TaskDocument> tasks;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public List<TaskDocument> getTasks() { return tasks; }
    public void setTasks(List<TaskDocument> tasks) { this.tasks = tasks; }
}
