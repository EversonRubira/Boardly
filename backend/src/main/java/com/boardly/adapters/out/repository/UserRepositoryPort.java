package com.boardly.domain.repository;

import com.boardly.domain.model.User;

import java.util.Optional;

public interface UserRepositoryPort {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    User save(User user);
}
