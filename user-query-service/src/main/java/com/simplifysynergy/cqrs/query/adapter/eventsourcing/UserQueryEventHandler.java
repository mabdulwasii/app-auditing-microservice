package com.simplifysynergy.cqrs.query.adapter.eventsourcing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplifysynergy.cqrs.common.domain.User;
import com.simplifysynergy.cqrs.common.enumeration.EventType;
import com.simplifysynergy.cqrs.common.event.UserCreateEvent;
import com.simplifysynergy.cqrs.common.event.UserDeleteEvent;
import com.simplifysynergy.cqrs.common.event.UserUpdateEvent;
import com.simplifysynergy.cqrs.query.usecase.UserQueryUseCase;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@Slf4j
public class UserQueryEventHandler {
    private final ObjectMapper objectMapper;
    private final UserQueryUseCase useCase;

    public UserQueryEventHandler(ObjectMapper objectMapper, UserQueryUseCase useCase) {
        this.objectMapper = objectMapper;
        this.useCase = useCase;
    }

    @KafkaListener(topics = "${spring.kafka.transferTopic}",  groupId = "${spring.kafka.groupId}",
            containerFactory = "kafkaListenerContainerFactory")
    public Mono<Void> consumeAuditEvent(ConsumerRecord<String, String> consumerRecord) {
        log.info("Consuming audit event {} ", consumerRecord);
        try {
            String message = consumerRecord.value();
            Map<String, String> userEvent = new ObjectMapper().readValue(message, Map.class);

            if (userEvent.get("type").equals(EventType.CREATE.name())) {
                UserCreateEvent event = objectMapper.readValue(message, UserCreateEvent.class);
                User user = event.user();

                return useCase.save(user)
                        .mapNotNull(savedUser -> {
                            log.info("Save user {} in user readonly db", savedUser);
                            return savedUser;
                        }).then();
            } else if (userEvent.get("type").equals(EventType.DELETE.name())) {
                UserDeleteEvent userDeleteEvent = objectMapper.readValue(message, UserDeleteEvent.class);
                User user = userDeleteEvent.user();
                log.info("User deleted with id {} ", user.getId());
                return useCase.deleteById(user.getId());
            } else {
                UserUpdateEvent event = objectMapper.readValue(message, UserUpdateEvent.class);
                User user = event.user();
                return useCase.update(user)
                        .map(updatedUser -> {
                            log.info("Updated user {} in user readonly db", updatedUser);
                            return updatedUser;
                        }).then();
            }
        } catch (Exception e) {
            log.error("Error while handling message", e);
            throw new KafkaException(e.getMessage(),e);
        }
    }

}
