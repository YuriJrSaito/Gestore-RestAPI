package com.yurisaito.gestore.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer{
    
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/api/v1/product/getAll").allowedOrigins("http://localhost:3000", "http://10.0.0.148:3000", "http://10.0.0.149:3000").allowedMethods("GET");
        registry.addMapping("/api/v1/product/gotOne").allowedOrigins("http://localhost:3000", "http://10.0.0.148:3000", "http://10.0.0.149:3000").allowedMethods("GET");
        registry.addMapping("/api/v1/product/create").allowedOrigins("http://localhost:3000", "http://10.0.0.148:3000", "http://10.0.0.149:3000").allowedMethods("POST");
        registry.addMapping("/api/v1/product/update").allowedOrigins("http://localhost:3000", "http://10.0.0.148:3000", "http://10.0.0.149:3000").allowedMethods("PUT");
        registry.addMapping("/api/v1/product/delete").allowedOrigins("http://localhost:3000", "http://10.0.0.148:3000", "http://10.0.0.149:3000").allowedMethods("DELETE");
    }
}
