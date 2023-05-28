package com.simplifysynergy.cqrs.query.adapter.repository;

import com.simplifysynergy.cqrs.common.domain.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserQueryRepository extends ReactiveCrudRepository<User, String> {}
