package com.hexagon.cloudgateway.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {


    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        UserDetails userJames = User.builder()
                .username("James")
                .password("1234")
                .passwordEncoder(str -> passwordEncoder().encode(str))
                .roles("USER")
                .build();

        UserDetails userAdmin = User.builder()
                .username("Admin")
                .password("12345")
                .passwordEncoder(str -> passwordEncoder().encode(str))
                .roles("ADMIN")
                .build();

        return new MapReactiveUserDetailsService(userAdmin, userJames);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception
    {
        http.httpBasic();
        http.formLogin();
        http.csrf().disable();
        http.authorizeExchange().pathMatchers(HttpMethod.GET, "/school/**").permitAll();
        http.authorizeExchange().pathMatchers(HttpMethod.GET, "/h2-console/**").permitAll();
        http.authorizeExchange().pathMatchers(HttpMethod.GET, "/student/**").authenticated();
        return http.build();
    }
}