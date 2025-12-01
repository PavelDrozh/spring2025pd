package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;

@PropertySource("classpath:properties.properties")
@Configuration
public class AppConfig {
    @Bean
    ClassPathResource resourceFile(@Value("${resource.file}") String resource) {
        return new ClassPathResource(resource);
    }
}
