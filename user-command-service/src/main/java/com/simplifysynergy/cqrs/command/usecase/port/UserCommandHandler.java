package com.simplifysynergy.cqrs.command.usecase.port;

import com.simplifysynergy.cqrs.command.domain.entity.User;
import reactor.core.publisher.Mono;

public interface UserCommandHandler {
    Mono<User> create(User user);
    Mono<User> update(User user);
    Mono<User> findById(String id);
}
