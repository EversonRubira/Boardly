package com.boardly.adapters.in.web.dto.project;

import java.time.LocalDate;

public record CreateProjectRequest(String title, String description, LocalDate startDate, LocalDate endDate) {}
