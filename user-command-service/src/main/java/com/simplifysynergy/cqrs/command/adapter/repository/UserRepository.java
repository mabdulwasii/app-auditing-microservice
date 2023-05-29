package com.simplifysynergy.cqrs.command.adapter.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<User, String> {}
