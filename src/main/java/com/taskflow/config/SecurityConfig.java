package com.taskflow.config;

import com.taskflow.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Uygulamanın güvenlik yapılandırmasını içeren sınıftır.
 * <p>Form tabanlı kimlik doğrulama, giriş sonrası yönlendirme ve şifreleme işlemleri burada tanımlanır.</p>
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    /**
     * Uygulamanın güvenlik filtre zincirini yapılandırır.
     * <p>
     * - /auth/** ve /api/tasks/** yollarına erişim izni verir.
     * - Diğer tüm istekler için kimlik doğrulaması ister.
     * - Giriş sonrası kullanıcıyı /dashboard sayfasına yönlendirir.
     * </p>
     *
     * @param http HTTP güvenlik yapılandırması
     * @return SecurityFilterChain yapılandırması
     * @throws Exception yapılandırma sırasında hata oluşursa
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/api/tasks/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/dashboard", true)
                        .permitAll()
                )
                .userDetailsService(userDetailsService)
                .build();
    }

    /**
     * Şifreleri Bcrypt algoritması ile hash'leyen PasswordEncoder bean'idir.
     *
     * @return BCryptPasswordEncoder örneği
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * AuthenticationManager bean'ini sağlar.
     *
     * @param config Authentication yapılandırması
     * @return AuthenticationManager nesnesi
     * @throws Exception yapılandırma hatası durumunda
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
