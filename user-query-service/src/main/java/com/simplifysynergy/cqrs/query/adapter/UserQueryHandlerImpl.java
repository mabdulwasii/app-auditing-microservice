package com.simplifysynergy.cqrs.query.adapter;

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

    private final UserQueryRepository repository;

    @Override
    public Mono<com.simplifysynergy.cqrs.query.domain.entity.User> findById(String id) {
        log.info("UserQueryHandlerImpl::findById(" + id + ")");
         return repository.findById(id);
    }

    @Override
    public Flux<com.simplifysynergy.cqrs.query.domain.entity.User> findAll() {
        log.info("UserQueryHandlerImpl::findAll");
        return repository.findAll();
    }

    @Override
    public Mono<com.simplifysynergy.cqrs.query.domain.entity.User> save(com.simplifysynergy.cqrs.query.domain.entity.User user) {
        log.info("UserQueryHandlerImpl::save {} ", user);
        return repository.save(user);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        log.info("UserQueryHandlerImpl::deleteById {} ", id);
        return repository.deleteById(id);
    }

    @Override
    public Mono<com.simplifysynergy.cqrs.query.domain.entity.User> update(com.simplifysynergy.cqrs.query.domain.entity.User user) {
        log.info("UserQueryHandlerImpl::update {} ", user);
        return repository.save(user)
            .map(it -> {
                log.info("UserQueryHandlerImpl::saved successfully {} ", it);
                return it;
            });
    }
}
