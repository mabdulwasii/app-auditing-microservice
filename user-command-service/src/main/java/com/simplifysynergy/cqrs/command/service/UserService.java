package com.simplifysynergy.cqrs.command.service;

import com.simplifysynergy.cqrs.command.repository.UserRepository;
import com.simplifysynergy.cqrs.entity.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository repository;

    public Mono<User> save(User user) {
        log.info("save: {}", user);
        return repository.save(user);
    }
}
