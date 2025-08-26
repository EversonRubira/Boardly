package com.boardly.config;

import com.boardly.adapters.out.repository.ProjectRepositoryAdapter;
import com.boardly.adapters.out.mongo.repository.SpringDataProjectRepository;
import com.boardly.adapters.out.mongo.mapper.ProjectMongoMapper;
import com.boardly.application.service.*;
import com.boardly.application.usecase.*;
import com.boardly.application.usecase.CreateTaskUseCase;
import com.boardly.domain.repository.ProjectRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    ProjectRepositoryPort projectRepositoryPort(SpringDataProjectRepository springRepo, ProjectMongoMapper mapper) {
        return new ProjectRepositoryAdapter(springRepo, mapper);
    }

    @Bean
    CreateProjectUseCase createProjectUseCase(ProjectRepositoryPort repo) {
        return new CreateProjectService(repo);
    }

    @Bean
    ListProjectsUseCase listProjectsUseCase(ProjectRepositoryPort repo) {
        return new ListProjectsService(repo);
    }

    @Bean
    public FindProjectByIdUseCase findProjectByIdUseCase(ProjectRepositoryPort port){
        return new FindProjectByIdService(port);
    }

    @Bean
    public UpdateProjectUseCase updateProjectUseCase(ProjectRepositoryPort port){
        return new UpdateProjectService(port);
    }

    @Bean
    public DeleteProjectUseCase deleteProjectUseCase(ProjectRepositoryPort port){
        return new DeleteProjectService(port);
    }


    @Bean
    public CreateTaskUseCase createTaskUseCase(ProjectRepositoryPort port) {
        return new CreateTaskService(port);
    }

    @Bean
    public MoveTaskUseCase moveTaskUseCase(ProjectRepositoryPort port) {
        return new MoveTaskService(port);
    }
}
