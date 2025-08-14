package com.ahmad.learning.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain customFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity

                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->
                        auth
                        .requestMatchers(HttpMethod.POST, "/api/customer").permitAll()
                                .requestMatchers("/api/customers").hasRole("ADMIN")
                                .requestMatchers("/api/customer/*").hasAnyRole("ADMIN","USER")

                                .anyRequest().authenticated()
                );

        //for basic auth
        httpSecurity.httpBasic(Customizer.withDefaults());

        //for form login
        httpSecurity.formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}
