package com.simplifysynergy.cqrs.query.adapter.repository;

import com.simplifysynergy.cqrs.common.domain.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends R2dbcRepository<User, String> {}
