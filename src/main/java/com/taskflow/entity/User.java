package com.taskflow.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Sisteme giriş yapan kullanıcıları temsil eden JPA entity sınıfıdır.
 * <p>Veritabanındaki "users" tablosuna karşılık gelir. Her kullanıcı benzersiz bir kullanıcı adına ve şifreye sahiptir.</p>
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * Kullanıcının benzersiz kimliği (primary key)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Kullanıcının kullanıcı adı (benzersiz ve zorunlu)
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * Kullanıcının şifresi (Bcrypt ile hashlenmiş)
     */
    @Column(nullable = false)
    private String password;
}
