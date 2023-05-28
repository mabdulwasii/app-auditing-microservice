package com.simplifysynergy.cqrs.command.usecase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.simplifysynergy.cqrs.command.adapter.eventsourcing.UserCommandEventHandler;
import com.simplifysynergy.cqrs.command.usecase.port.UserCommandHandler;
import com.simplifysynergy.cqrs.common.domain.User;
import com.simplifysynergy.cqrs.common.event.UserCreateEvent;
import com.simplifysynergy.cqrs.common.event.UserDeleteEvent;
import com.simplifysynergy.cqrs.common.event.UserUpdateEvent;
import com.simplifysynergy.cqrs.common.util.UserMapper;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class UserCommandUseCase {

    private final UserCommandHandler commandHandler;
    private final UserCommandEventHandler eventHandler;
    public Mono<User> create(User user) {
        log.info("UserCommandUseCase : create {}", user);
        if (!StringUtils.isEmpty(user.getId())) {
            throw new IllegalArgumentException("User must not have an id = " + user.getId());
        }
        user.setId(UUID.randomUUID().toString());
        return commandHandler.create(user)
                .map( savedUser -> {
                    UserCreateEvent event = new UserCreateEvent(user);
                    try {
                        eventHandler.publishEvent(event);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    return savedUser;
                });

    }

    public Mono<User> update(User user) {
        log.info("UserCommandUseCase : update {}", user);
        if (user == null || StringUtils.isBlank(user.getId())) {
            throw new NullPointerException("Invalid user =  " + user);
        }
        return commandHandler.findById(user.getId())
                .map(retrievedUser -> {
                    UserMapper.mapToUser(retrievedUser, user);
                    commandHandler.update(retrievedUser)
                            .map(updatedUser -> {
                                UserUpdateEvent event = new UserUpdateEvent(user);
                                try {
                                    eventHandler.publishEvent(event);
                                } catch (JsonProcessingException e) {
                                    throw new RuntimeException(e);
                                }
                                return updatedUser;
                            });
                    return retrievedUser;
                });
    }

    public Mono<Void> delete(String id) {
        log.info("UserCommandUseCase : delete {}", id);
        return commandHandler.deleteById(id)
                .mapNotNull(unused -> {
                    User user = new User();
                    user.setId(id);
                    UserDeleteEvent event = new UserDeleteEvent(user);
                    try {
                        eventHandler.publishEvent(event);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    return unused;
                }).then();
    }
}
