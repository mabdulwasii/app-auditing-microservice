package com.simplifysynergy.cqrs.command.repository;

import com.simplifysynergy.cqrs.entity.domain.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<User, String> {
	
}
