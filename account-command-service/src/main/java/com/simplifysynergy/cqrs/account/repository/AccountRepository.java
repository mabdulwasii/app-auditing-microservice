package com.simplifysynergy.cqrs.account.repository;

import com.simplifysynergy.cqrs.domain.Account;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AccountRepository extends ReactiveCrudRepository<Account, String> {}
