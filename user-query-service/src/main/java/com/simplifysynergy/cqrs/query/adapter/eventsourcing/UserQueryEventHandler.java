package com.simplifysynergy.cqrs.query.adapter.eventsourcing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplifysynergy.cqrs.common.domain.User;
import com.simplifysynergy.cqrs.common.event.Event;
import com.simplifysynergy.cqrs.query.usecase.UserQueryUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@AllArgsConstructor
public class UserQueryEventHandler {

    private final ObjectMapper objectMapper;
    private final UserQueryUseCase useCase;

    @KafkaListener(topics = "${spring.kafka.transferTopic}", groupId = "${spring.kafka.groupId}",
            containerFactory = "kafkaListenerContainerFactory")
    public Mono<Void> consumeAuditEvent(ConsumerRecord<String, String> consumerRecord) {
        log.info("Consuming audit event {} ", consumerRecord);

        String message = consumerRecord.value();
        Event event = null;
        try {
            event = objectMapper.readValue(message, Event.class);
        } catch (Exception e) {
            throw new IllegalStateException("Could not parse event message");
        }
        log.info("processing event {} ", event);
        User user = event.getUser();
        switch (event.getType()) {
            case CREATE -> {
                return useCase.save(user)
                        .mapNotNull(savedUser -> {
                            log.info("Save user {} in user readonly db", savedUser);
                            return savedUser;
                        }).then();
            }
            case UPDATE -> {
                return useCase.update(user)
                        .map(updatedUser -> {
                            log.info("Updated user {} in user readonly db", updatedUser);
                            return updatedUser;
                        }).then();
            }
            case DELETE -> {
                log.info("Delete user with id {} ", user.getId());
                return useCase.deleteById(user.getId());
            }
            default -> throw new IllegalStateException("Invalid event type: " + event.getType());
        }
    }
}
