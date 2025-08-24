package com.boardly.adapters.out;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.boardly.adapters.out.mongo.document.ProjectDocument;

public interface SpringDataProjectRepository extends MongoRepository<ProjectDocument, String> { }
