package com.boardly.adapters.in.web.dto.task;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TaskResponse {

    private String id;
    private String title;
    private String assignee;
    private String status;

    public TaskResponse(String id, String title, String assignee, String status) {
        this.id = id;
        this.title = title;
        this.assignee = assignee;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAssignee() {
        return assignee;
    }

    public String getStatus() {
        return status;
    }
}
