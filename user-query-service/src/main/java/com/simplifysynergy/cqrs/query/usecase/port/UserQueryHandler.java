package com.simplifysynergy.cqrs.query.usecase.port;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserQueryHandler {
    Mono<User> findById(String id);

    Flux<User> findAll();

    Mono<User> save(User user);

    Mono<Void> deleteById(String id);

    Mono<User> update(User user);
}
