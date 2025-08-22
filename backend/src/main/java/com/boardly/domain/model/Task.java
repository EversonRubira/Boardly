package com.boardly.domain.model;

import com.boardly.domain.common.Status;

public class Task {
    private String id;
    private String title;
    private String assignee; // responsável
    private Status status = Status.TO_DO;

    public Task(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("task title is required");
        }
        this.title = title;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }

    public String getAssignee() { return assignee; }
    public void setAssignee(String assignee) { this.assignee = assignee; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}
