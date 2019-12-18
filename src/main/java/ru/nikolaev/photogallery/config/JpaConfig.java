package ru.nikolaev.photogallery.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EntityScan(
        basePackages = {
                "ru.nikolaev.photogallery.model"
        })
@EnableJpaRepositories(
        basePackages = {
                "ru.nikolaev.photogallery.repositories"
        })
public class JpaConfig {
}
