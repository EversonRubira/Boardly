package com.boardly.g_mapper;


import com.boardly.f_dto.ProjectDTO;
import com.boardly.e_model.Project;

public class ProjectMapper {

    public static ProjectDTO toDTO(Project entity) {
        return new ProjectDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getTasks()
        );
    }

    public static Project toEntity(ProjectDTO dto) {
        return Project.builder()
                .id(dto.id())
                .title(dto.title())
                .description(dto.description())
                .startDate(dto.startDate())
                .endDate(dto.endDate())
                .tasks(dto.tasks())
                .build();
    }

}
