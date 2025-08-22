package com.boardly.application.usecase;

import java.time.LocalDate;
import java.util.List;

public interface ListProjectsUseCase {
    List<Output> execute();

    record Output(String id, String title, String description, LocalDate startDate, LocalDate endDate) {}
}
