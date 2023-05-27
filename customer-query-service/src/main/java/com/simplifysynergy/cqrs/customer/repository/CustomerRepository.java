package com.simplifysynergy.cqrs.customer.repository;

import com.simplifysynergy.cqrs.domain.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, String> {
	
}
