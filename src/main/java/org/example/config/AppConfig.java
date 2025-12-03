package org.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableConfigurationProperties(PropertyConfig.class)
public class AppConfig {

    @ConditionalOnProperty(value = "spring.application.locale", havingValue = "en-US")
    @Bean
    ClassPathResource resourceFileEn(@Autowired PropertyConfig path) {
        return new ClassPathResource(path.getEn());
    }
    @ConditionalOnProperty(value = "spring.application.locale", havingValue = "ru-RU")
    @Bean
    ClassPathResource resourceFileRu(@Autowired PropertyConfig path) {
        return new ClassPathResource(path.getRu());
    }
}

