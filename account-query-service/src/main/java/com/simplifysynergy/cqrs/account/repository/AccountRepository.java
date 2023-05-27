package com.simplifysynergy.cqrs.account.repository;


import com.simplifysynergy.cqrs.domain.Account;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface AccountRepository extends ReactiveCrudRepository<Account, String> {
	Flux<Account> findByCustomerId(String customerId);
	
}
