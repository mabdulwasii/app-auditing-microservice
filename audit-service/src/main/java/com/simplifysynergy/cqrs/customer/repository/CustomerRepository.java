package com.simplifysynergy.cqrs.customer.repository;

import com.simplifysynergy.cqrs.customer.model.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, String> {
	
}
