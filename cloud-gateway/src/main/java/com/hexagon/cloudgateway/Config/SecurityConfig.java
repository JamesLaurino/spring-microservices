package com.hexagon.cloudgateway.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    private ReactiveUserDetailsService reactiveUserDetailsService;


    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception
    {
        http.httpBasic();
        http.formLogin();
        http.csrf().disable();
        http.authorizeExchange().pathMatchers(HttpMethod.GET, "/school/**").permitAll();
        http.authorizeExchange().pathMatchers(HttpMethod.GET, "/student/**").authenticated();
        return http.build();
    }
}