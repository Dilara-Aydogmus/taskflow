package com.taskflow.dto;

import lombok.Data;

/**
 * Giriş işlemi sırasında kullanılan veri transfer nesnesidir (DTO).
 * <p>Kullanıcının kullanıcı adı ve şifresini içerir.</p>
 */
@Data
public class LoginRequest {
    private String username;
    private String password;
}
