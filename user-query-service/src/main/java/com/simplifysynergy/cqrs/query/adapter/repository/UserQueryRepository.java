package com.simplifysynergy.cqrs.query.adapter.repository;


import com.simplifysynergy.cqrs.query.domain.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserQueryRepository extends ReactiveCrudRepository<User, String> {}
