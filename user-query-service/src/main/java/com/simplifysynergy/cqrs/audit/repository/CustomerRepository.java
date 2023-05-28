package com.simplifysynergy.cqrs.audit.repository;

import com.simplifysynergy.cqrs.entity.domain.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerRepository extends ReactiveCrudRepository<User, String> {
	
}
