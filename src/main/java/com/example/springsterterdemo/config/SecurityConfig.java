package com.example.springsterterdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(c -> c.disable())
                .cors(c -> c.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/api/rest/welcome").permitAll())
                .authorizeHttpRequests(auth-> auth.anyRequest().authenticated())
                .formLogin(form -> {
                            form.loginPage("/login").permitAll();
                            form.successForwardUrl("/login/success").;
                        }
                );
        // ..
        return http.build();
    }
}
