package com.example.todobasic.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.todobasic.interceptors.AuthInterceptor;
 @Configuration
public class WebAuthConfiguration implements WebMvcConfigurer {

    @Autowired
    AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Register the AuthInterceptor for multiple paths
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/todos/**"); // Add the first path pattern
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/home/**"); // Add the second path pattern
        // Add more path patterns as needed
    }
}
