package com.boardly.adapters.out.mongo.document;

public class TaskDocument {

    private String id;
    private String title;
    private String assignee; //  campo necessário!
    private String status;

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAssignee() { return assignee; }
    public void setAssignee(String assignee) { this.assignee = assignee; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
