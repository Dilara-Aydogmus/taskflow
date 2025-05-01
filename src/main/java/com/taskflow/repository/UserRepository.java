package com.taskflow.repository;

import com.taskflow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * User entity'si için veritabanı erişim katmanıdır.
 * <p>Kullanıcıyı username ile sorgulamak için özel metot içerir.</p>
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Kullanıcı adını baz alarak kullanıcıyı bulur.
     *
     * @param username Kullanıcı adı
     * @return Bulunan kullanıcı, yoksa boş
     */
    Optional<User> findByUsername(String username);
}
