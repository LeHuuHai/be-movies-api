package dev.hari.movies;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig {
    @Value("${app.cors.allowed-origins}")
    private String allowedOrigins;

    @Bean
    public WebMvcConfigurer corsConfig(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                String[] origins = allowedOrigins.split(",");
                registry.addMapping("/**")
                        .allowedOrigins(origins)
                        .allowedMethods("GET")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
