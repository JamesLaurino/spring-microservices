package com.hexagon.cloudgateway.repository;

import com.hexagon.cloudgateway.dto.UserDto;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<UserDto,Integer> {
    Mono<UserDto> findByUsername(String username);
}