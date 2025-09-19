package com.boardly.adapters.out.repository;

import com.boardly.adapters.out.mongo.document.UserDocument;
import com.boardly.adapters.out.mongo.mapper.UserMongoMapper;
import com.boardly.adapters.out.mongo.repository.SpringDataUserRepository;
import com.boardly.domain.model.User;
import com.boardly.application.ports.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final SpringDataUserRepository repo;

    @Override
    public Optional<User> findByEmail(String email) {
        return repo.findByEmail(email).map(UserMongoMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        UserDocument doc = UserMongoMapper.toDocument(user);
        return UserMongoMapper.toDomain(repo.save(doc));
    }
}
