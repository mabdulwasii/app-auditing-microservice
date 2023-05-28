package com.simplifysynergy.cqrs.query.usecase;

import com.simplifysynergy.cqrs.common.domain.User;
import com.simplifysynergy.cqrs.query.usecase.port.UserQueryHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class UserQueryUseCase {

    private final UserQueryHandler userQueryHandler;

    public Mono<User> findById(String id) {
        log.info("findById: id={}", id);
        return userQueryHandler.findById(id);
    }

    public Flux<User> findAll() {
        log.info("findAll");
        return userQueryHandler.findAll();
    }

    public Mono<User> save(User user) {
        log.info("UserQueryUseCase::save {} ", user);
        return userQueryHandler.save(user);
    }

    public Mono<Void> deleteById(String id) {
        return userQueryHandler.deleteById(id);
    }

    public Mono<User> update(User user) {
        return userQueryHandler.update(user);
    }
}
