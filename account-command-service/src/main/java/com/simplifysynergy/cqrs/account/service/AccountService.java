package com.simplifysynergy.cqrs.account.service;

import com.simplifysynergy.cqrs.domain.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.simplifysynergy.cqrs.account.repository.AccountRepository;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class AccountService {

    private AccountRepository repository;

    public Mono<Account> save(Account account) {
        return repository.save(account);
    }

}
