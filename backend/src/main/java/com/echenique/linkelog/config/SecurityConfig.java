package com.echenique.linkelog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain  filterChain(HttpSecurity http) throws Exception{
        http.csrf(c -> c.disable()
        ).httpBasic(Customizer.withDefaults()).authorizeHttpRequests(authorize -> {
            authorize.requestMatchers(HttpMethod.POST,"/user/new").permitAll()
                    .requestMatchers("/error").permitAll()
                    .anyRequest().authenticated();
        });

        return http.build();
    }


}
