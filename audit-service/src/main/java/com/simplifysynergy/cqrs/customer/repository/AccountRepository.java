package com.simplifysynergy.cqrs.customer.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.simplifysynergy.cqrs.customer.model.Account;
import reactor.core.publisher.Flux;

public interface AccountRepository extends ReactiveCrudRepository<Account, String> {

	Flux<Account> findByCustomerId(String customerId);
	
}
