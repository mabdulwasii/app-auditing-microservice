package com.simplifysynergy.cqrs.query.service;

import com.simplifysynergy.cqrs.query.repository.CustomerRepository;
import com.simplifysynergy.cqrs.entity.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository repository;
    private final WebClient.Builder webClientBuilder;

    public Mono<User> findById(@PathVariable("id") String id) {
        log.info("findById: id={}", id);
        return repository.findById(id);
    }

    @GetMapping("/")
    public Flux<User> findAll() {
        log.info("findAll");
        return repository.findAll();
    }

    public Mono<User> findByIdWithAccounts(@PathVariable("id") String id) {
        log.info("findByIdWithAccounts: id={}", id);
            return repository.findById(id);
    }
}
