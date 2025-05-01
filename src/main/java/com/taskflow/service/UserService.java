package com.taskflow.service;

import com.taskflow.dto.LoginRequest;
import com.taskflow.entity.User;
import com.taskflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Kullanıcı kayıt ve giriş işlemlerini yöneten servis sınıfıdır.
 * <p>Kullanıcının veritabanına kaydedilmesi ve kimlik doğrulama işlemleri bu sınıfta gerçekleştirilir.</p>
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Yeni bir kullanıcıyı veritabanına kaydeder.
     *
     * @param user Kayıt olacak kullanıcı bilgisi
     * @return Başarılı kayıt mesajı
     */
    public ResponseEntity<String> register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("Kayıt başarılı.");
    }

    /**
     * Kullanıcı adı ve şifreye göre giriş yapar.
     *
     * @param loginRequest Giriş bilgileri
     * @return Başarılıysa JWT token mesajı, aksi halde hata mesajı
     */
    public ResponseEntity<String> login(LoginRequest loginRequest) {
      Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
      if (user.isPresent() && passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
          return ResponseEntity.ok("Giriş başarılı.");
       }
       return ResponseEntity.status(401).body("Geçersiz kullanıcı adı veya şifre.");
   }
}
