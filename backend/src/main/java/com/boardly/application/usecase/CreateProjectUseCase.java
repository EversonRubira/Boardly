package com.boardly.application.usecase;

import java.time.LocalDate;

public interface CreateProjectUseCase {
    Output execute(Input input);

    record Input(String title, String description, LocalDate startDate, LocalDate endDate) {}
    record Output(String id, String title, String description, LocalDate startDate, LocalDate endDate) {}

}
