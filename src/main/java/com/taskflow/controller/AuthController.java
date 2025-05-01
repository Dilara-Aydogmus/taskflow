package com.taskflow.controller;

import com.taskflow.dto.LoginRequest;
import com.taskflow.entity.User;
import com.taskflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Kullanıcı kayıt ve giriş işlemleri için HTTP isteklerini yöneten controller sınıfıdır.
 * <p>Giriş yapan kullanıcıya JWT benzeri bir yanıt döner.</p>
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    /**
     * Yeni kullanıcı kaydı oluşturur.
     *
     * @param user Kayıt olacak kullanıcı bilgisi (JSON)
     * @return Başarılı kayıt mesajı
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        return userService.register(user);
    }

    /**
     * Kullanıcı girişini gerçekleştirir.
     *
     * @param loginRequest Kullanıcı adı ve şifre içeren giriş isteği
     * @return Başarılı giriş mesajı (JWT token içerir)
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }
}
