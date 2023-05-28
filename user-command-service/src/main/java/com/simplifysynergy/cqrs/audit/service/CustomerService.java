package com.simplifysynergy.cqrs.audit.service;

import com.simplifysynergy.cqrs.audit.repository.CustomerRepository;
import com.simplifysynergy.cqrs.entity.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository repository;

    public Mono<User> save(User user) {
        log.info("save: {}", user);
        return repository.save(user);
    }
}
