package com.boardly.d_repository;

import com.boardly.e_model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<Note, String> {
    // Aqui você pode adicionar métodos customizados no futuro (ex: findByTitle)
}
