package com.boardly.e_model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    private String id;

    private String title;
    private String description;
    private String assignedTo;

    private Status status;

    private LocalDate dueDate;

    public enum Status {
        TO_DO,
        DOING,
        DONE
    }
}
