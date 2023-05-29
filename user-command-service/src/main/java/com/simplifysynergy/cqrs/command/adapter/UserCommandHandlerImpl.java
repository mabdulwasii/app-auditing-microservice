package com.simplifysynergy.cqrs.command.adapter;

import com.simplifysynergy.cqrs.command.adapter.repository.UserRepository;
import com.simplifysynergy.cqrs.command.domain.entity.User;
import com.simplifysynergy.cqrs.command.usecase.port.UserCommandHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@AllArgsConstructor
public class UserCommandHandlerImpl implements UserCommandHandler {

    private final UserRepository repository;

    @Override
    public Mono<User> create(User user) {
        log.info("Updating create {} ", user);
        return repository.save(user);
    }

    @Override
    public Mono<User> update(User user) {
        log.info("Updating user {} ", user);
        return repository.save(user);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        log.info("Delete by Id {} ", id);
        return repository.deleteById(id);
    }
    @Override
    public Mono<User> findById(String id) {
        log.info("UserCommandHandlerImpl::findById {} ", id);
        return repository.findById(id);
    }

}
