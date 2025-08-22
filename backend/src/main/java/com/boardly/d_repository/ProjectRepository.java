package com.boardly.d_repository;

import com.boardly.e_model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String>{
    // Podemos adicionar métodos customizados aqui no futuro, ex:
    // List<Project> findByTitleContainingIgnoreCase(String title);

}
