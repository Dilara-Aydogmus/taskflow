package com.taskflow.controller;

import com.taskflow.entity.User;
import com.taskflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Kullanıcı verilerine ilişkin işlemleri sağlayan controller sınıfıdır.
 * <p>Kullanıcı adını kullanarak kullanıcı bilgisi sorgulama işlemini içerir.</p>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/by-username/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(
                userRepository.findByUsername(username)
                        .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı: " + username))
        );
    }
}