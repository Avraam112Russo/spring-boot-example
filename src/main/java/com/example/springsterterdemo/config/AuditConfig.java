package com.example.springsterterdemo.config;

import com.example.springsterterdemo.SpringRunnerApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.envers.repository.config.EnableEnversRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
// enable jpa auditing
@EnableJpaAuditing(modifyOnCreate = false) // field updated_at doesn't update when created object
@EnableEnversRepositories(basePackageClasses = SpringRunnerApplication.class)
public class AuditConfig {
    @Bean
    public AuditorAware<String> auditorAware() {
        // securityContext.getCurrentUser.getId in Production
        return () -> Optional.of("AVRAAM_RUSSO_ADMIN");
    }
}
