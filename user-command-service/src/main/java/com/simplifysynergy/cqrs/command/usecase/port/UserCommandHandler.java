package com.simplifysynergy.cqrs.command.usecase.port;

import com.simplifysynergy.cqrs.common.domain.User;
import reactor.core.publisher.Mono;

public interface UserCommandHandler {
    Mono<User> create(User user);
    Mono<User> update(User user);
    Mono<Void> deleteById(String id);
    Mono<User> findById(String id);
}
