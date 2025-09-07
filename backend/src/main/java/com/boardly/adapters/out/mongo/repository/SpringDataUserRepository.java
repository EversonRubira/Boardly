package com.boardly.adapters.out.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.boardly.adapters.out.mongo.document.UserDocument;

import java.util.Optional;

public interface SpringDataUserRepository extends MongoRepository<UserDocument, String> {
    Optional<UserDocument> findByEmail(String email);
    boolean existsByEmail(String email);
}
