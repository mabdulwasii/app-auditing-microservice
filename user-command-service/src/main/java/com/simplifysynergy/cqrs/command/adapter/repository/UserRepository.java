package com.simplifysynergy.cqrs.command.adapter.repository;

import com.simplifysynergy.cqrs.command.domain.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<User, String> {}
