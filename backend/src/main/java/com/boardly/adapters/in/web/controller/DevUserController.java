
package com.boardly.adapters.in.web.controller;


import com.boardly.domain.model.User;
import com.boardly.domain.repository.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dev/users")
@RequiredArgsConstructor
public class DevUserController {
    private final UserRepositoryPort users;

    @PostMapping
    public User create(@RequestBody User u) { return users.save(u); }

    @GetMapping("/by-email")
    public ResponseEntity<User> byEmail(@RequestParam String email) {
        return users.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
