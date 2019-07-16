package org.adam.aliasswitcher;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
/**
        registry.addMapping("/**")
                .allowedOrigins("https//localhost:8080")
                .allowedMethods("GET", "PUT", "POST", "DELETE")
                .allowedHeaders("**", "header2", "header3")
                .exposedHeaders("header1", "header2")
                .allowCredentials(true).maxAge(3600);

        // Add more mappings...

 **/
    }
}