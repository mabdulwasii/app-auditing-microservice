package com.simplifysynergy.cqrs.query.adapter.eventsourcing;


import com.simplifysynergy.cqrs.common.Event;
import com.simplifysynergy.cqrs.query.adapter.repository.UserRepository;
import com.simplifysynergy.cqrs.query.domain.entity.User;
import com.simplifysynergy.cqrs.query.domain.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@AllArgsConstructor
public class UserQueryEventHandler {

    private final UserRepository useCase;

    @KafkaListener(topics = "${spring.kafka.transferTopic}", groupId = "${spring.kafka.groupId}")
    public Mono<Void> consumeUserEvent(Event event) {

        log.info("Consuming user event {} ", event);

        User user = UserMapper.mapUserEntityToUserDto(event.getUserDto());
        switch (event.getType()) {
            case CREATE -> {
                return useCase.save(user)
                        .map(savedUser -> {
                            log.info("consumeUserEvent::Save user {} in user readonly db", savedUser);
                            return savedUser;
                        }).then();
            }
            case UPDATE -> {
                return useCase.save(user)
                        .map(updatedUser -> {
                            log.info("consumeUserEvent::Updated user {} in user readonly db", updatedUser);
                            return updatedUser;
                        }).then();
            }
            case DELETE -> {
                log.info("consumeUserEvent:: Delete user with id {} ", user.getId());
                return useCase.deleteById(user.getId());
            }
            default -> throw new IllegalStateException("Invalid event type: " + event.getType());
        }
    }
}
