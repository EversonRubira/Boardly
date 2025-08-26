package com.boardly.adapters.out.mongo.mapper;

import java.util.stream.Collectors;
import java.util.List;

import com.boardly.adapters.out.mongo.document.ProjectDocument;
import com.boardly.adapters.out.mongo.document.TaskDocument;
import com.boardly.domain.common.Status;
import com.boardly.domain.model.Project;
import com.boardly.domain.model.Task;
import org.springframework.stereotype.Component;

@Component
public class ProjectMongoMapper {

    // Domain -> Document
    public ProjectDocument toDocument(Project p) {
        ProjectDocument d = new ProjectDocument();
        d.setId(p.getId());
        d.setTitle(p.getTitle());
        d.setDescription(p.getDescription());
        d.setStartDate(p.getStartDate());
        d.setEndDate(p.getEndDate());

        if (p.getTasks() != null) {
            List<TaskDocument> taskDocs = p.getTasks().stream().map(t -> {
                TaskDocument td = new TaskDocument();
                td.setId(t.getId());
                td.setTitle(t.getTitle());
                td.setAssignee(t.getAssignee()); // <<< mapeia responsável
                td.setStatus(t.getStatus() != null ? t.getStatus().name() : null); // <<< null-safe
                return td;
            }).collect(Collectors.toList());
            d.setTasks(taskDocs);
        }
        return d;
    }

    // Document -> Domain
    public Project toDomain(ProjectDocument d) {
        Project p = new Project(d.getTitle(), d.getDescription(), d.getStartDate(), d.getEndDate());
        p.setId(d.getId());

        if (d.getTasks() != null) {
            for (TaskDocument td : d.getTasks()) {
                Task t = new Task(td.getTitle());
                t.setId(td.getId());
                t.setAssignee(td.getAssignee()); // <<< mapeia responsável
                t.setStatus(td.getStatus() != null ? Status.valueOf(td.getStatus()) : null); // <<< null-safe
                p.getTasks().add(t);
            }
        }
        return p;
    }
}
