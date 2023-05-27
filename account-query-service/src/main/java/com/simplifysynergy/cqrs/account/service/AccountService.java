package com.simplifysynergy.cqrs.account.service;

import com.simplifysynergy.cqrs.account.repository.AccountRepository;
import com.simplifysynergy.cqrs.domain.Account;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class AccountService {
    private final AccountRepository repository;

    public Flux<Account> findByCustomer(String customerId) {
        log.info("AccountService findByCustomer: customerId = {}", customerId);
        return repository.findByCustomerId(customerId);
    }

    public Flux<Account> findAll() {
        log.info("AccountService findAll");
        return repository.findAll();
    }

    public Mono<Account> findById(String id) {
        log.info("AccountService findById: id={}", id);
        return repository.findById(id);
    }
}
