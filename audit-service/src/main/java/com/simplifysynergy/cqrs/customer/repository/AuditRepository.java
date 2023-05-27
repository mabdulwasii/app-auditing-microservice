package com.simplifysynergy.cqrs.customer.repository;


import com.simplifysynergy.cqrs.domain.Account;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AuditRepository extends ReactiveCrudRepository<Account, String> {}
