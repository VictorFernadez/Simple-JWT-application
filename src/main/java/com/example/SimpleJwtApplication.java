package com.example;

import com.example.security.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class SimpleJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleJwtApplication.class, args);
    }

    @EnableWebSecurity
    @Configuration
    class SecurityConfiguration {
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf.disable())
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers(HttpMethod.POST, "/user").permitAll()
                            .anyRequest().authenticated());


            return http.build();
        }

    }


}
