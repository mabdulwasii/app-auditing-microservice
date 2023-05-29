package com.simplifysynergy.cqrs.query.adapter.eventsourcing;


import com.simplifysynergy.cqrs.common.Event;
import com.simplifysynergy.cqrs.query.domain.mapper.UserMapper;
import com.simplifysynergy.cqrs.query.usecase.UserQueryUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@AllArgsConstructor
public class UserQueryEventHandler {

    private final UserQueryUseCase userQueryUseCase;

    @KafkaListener(topics = "${spring.kafka.transferTopic}", groupId = "${spring.kafka.groupId}")
    public Mono<Void> consumeUserEvent(Event event) {

        log.info("Consuming user event {} ", event);

        com.simplifysynergy.cqrs.query.domain.entity.User user = UserMapper.mapUserEntityToUserDto(event.getUserDto());
        log.info("user to be saved {} ", user);
        switch (event.getType()) {
            case CREATE -> {
                return userQueryUseCase.save(user)
                        .map(savedUser -> {
                            log.info("consumeUserEvent::Save user {} in user readonly db", savedUser);
                            return savedUser;
                        }).then();
            }
            case UPDATE -> {
                return userQueryUseCase.update(user)
                        .map(updatedUser -> {
                            log.info("consumeUserEvent::Updated user {} in user readonly db", updatedUser);
                            return updatedUser;
                        }).then();
            }
            default -> throw new IllegalStateException("Invalid event type: " + event.getType());
        }
    }
}
