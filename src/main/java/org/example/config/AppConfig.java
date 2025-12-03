package org.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableConfigurationProperties(PropertyConfig.class)
public class AppConfig {

    @Bean
    ClassPathResource resourceFile(@Autowired PropertyConfig path) {
        return new ClassPathResource(path.getFile());
    }
}

