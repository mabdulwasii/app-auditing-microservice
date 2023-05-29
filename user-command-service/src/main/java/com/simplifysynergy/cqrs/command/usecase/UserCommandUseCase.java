package com.simplifysynergy.cqrs.command.usecase;

import com.simplifysynergy.cqrs.command.adapter.eventsourcing.UserCommandEventHandler;
import com.simplifysynergy.cqrs.command.domain.entity.User;
import com.simplifysynergy.cqrs.command.domain.mapper.UserMapper;
import com.simplifysynergy.cqrs.command.usecase.port.UserCommandHandler;
import com.simplifysynergy.cqrs.common.Event;
import com.simplifysynergy.cqrs.common.UserDto;
import com.simplifysynergy.cqrs.common.enumeration.EventType;
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
            throw new IllegalArgumentException("UserDto must not have an id = " + user.getId());
        }
        user.setId(UUID.randomUUID().toString());
        log.info("UserCommandUseCase : updated user {}", user);
       return commandHandler.create(user)
                .map(savedUser -> {
                    log.info("UserCommandUseCase::create saved user {}", savedUser);
                    Event event = new Event(UserMapper.mapUserEntityToUserDto(savedUser), EventType.CREATE);
                    log.info("UserCommandUseCase::create event request created {}", event);
                    eventHandler.publishEvent(event);
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
                    log.info("findById::retrievedUser {} ->", retrievedUser);
                    UserMapper.mapToUser(retrievedUser, user);
                    return retrievedUser;
                }).flatMap(retrievedUser -> commandHandler.update(retrievedUser)
                        .map(updatedUser -> {
                            log.info("updatedUser:: {} ", updatedUser);
                            Event event = new Event(UserMapper.mapUserEntityToUserDto(updatedUser), EventType.UPDATE);
                            log.info("UserUpdateIEvent:: {} ", event);
                            eventHandler.publishEvent(event);
                            return updatedUser;
                        }));
    }

    public Mono<Void> delete(String id) {
        log.info("UserCommandUseCase : delete {}", id);
        UserDto user = new UserDto();
        user.setId(id);
        Event event = new Event(user, EventType.DELETE);

        Mono<Void> voidMono = commandHandler.deleteById(id);
        eventHandler.publishEvent(event);
        return voidMono;
    }
}
