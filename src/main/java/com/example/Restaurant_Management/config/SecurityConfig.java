package com.example.Restaurant_Management.config;


import com.example.Restaurant_Management.auth.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/auth/guest/login")
                        .permitAll()
                        .requestMatchers("/api/auth/guest/register")
                        .permitAll()
                        .requestMatchers("api/guest/**")
                        .permitAll()
                        .requestMatchers("/api/customer/**")
                        .hasRole("CUSTOMER")
                        .requestMatchers("api/manager/**")
                        .hasRole("MANAGER")
                        .requestMatchers("api/admin/**")
                        .hasRole("ADMIN")
                        // Cho phép truy cập không cần login vào file chat.html và các file static khác
                        .requestMatchers("/chat.html", "/js/**", "/css/**", "/images/**").permitAll()
                        // Cho phép truy cập WebSocket endpoint
                        .requestMatchers("/ws/**").permitAll()
                        .anyRequest()
                        .authenticated())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
