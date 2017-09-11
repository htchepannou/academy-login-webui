package io.tchepannou.www.academy.login.config;

import io.tchepannou.www.academy.login.health.UrlHealthCheck;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HealthCheckConfiguration {

    @Bean
    UrlHealthCheck academyBackendHealthCheck(@Value("${backend.user.url}") final String url) {
        return new UrlHealthCheck(url + "/health", 15000);
    }

}
