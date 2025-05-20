package com.example.Restaurant_Management.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Áp dụng CORS cho các API có đường dẫn bắt đầu bằng /api/
                .allowedOrigins("http://localhost:3000", "http://192.168.1.4:3000") // Cho phép hai địa chỉ
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Các method HTTP được phép
                .allowedHeaders("*") // Cho phép tất cả các header
                .allowCredentials(true); // Cho phép gửi cookie nếu cần
    }
}
