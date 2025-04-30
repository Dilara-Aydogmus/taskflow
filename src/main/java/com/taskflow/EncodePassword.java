package com.taskflow;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodePassword {
    public static void main(String[] args) {
      //  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
       // String rawPassword = "12345";
       // String hashedPassword = encoder.encode(rawPassword);
       // System.out.println("Encoded password: " + hashedPassword);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("12345")); // Şifrem
    }
}
