package com.simplifysynergy.cqrs.query.adapter;

import com.simplifysynergy.cqrs.common.domain.User;
import com.simplifysynergy.cqrs.query.adapter.repository.UserQueryRepository;
import com.simplifysynergy.cqrs.query.usecase.port.UserQueryHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
@Slf4j
@AllArgsConstructor
public class UserQueryHandlerImpl implements UserQueryHandler {
    private static UserQueryRepository repository;

    @Override
    public Mono<User> findById(String id) {
        log.info("UserQueryHandlerImpl::findById(" + id + ")");
        return repository.findById(id);
    }

    @Override
    public Flux<User> findAll() {
        log.info("UserQueryHandlerImpl::findAll");
        return repository.findAll();
    }

    @Override
    public Mono<User> save(User user) {
        log.info("UserQueryHandlerImpl::save {} ", user);
        return repository.save(user);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        log.info("UserQueryHandlerImpl::deleteById {} ", id);
        return repository.deleteById(id);
    }

    @Override
    public Mono<User> update(User user) {
        log.info("UserQueryHandlerImpl::update {} ", user);
        return repository.save(user);
    }
}
