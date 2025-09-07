package com.boardly.legacy.d_repository;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<com.boardly.e_model.Note, String> {
    // Aqui você pode adicionar métodos customizados no futuro (ex: findByTitle)
}
