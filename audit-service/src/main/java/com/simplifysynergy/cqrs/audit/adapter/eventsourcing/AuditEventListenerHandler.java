package com.simplifysynergy.cqrs.audit.adapter.eventsourcing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplifysynergy.cqrs.audit.usecase.AuditUseCase;
import com.simplifysynergy.cqrs.common.domain.Audit;
import com.simplifysynergy.cqrs.common.domain.User;
import com.simplifysynergy.cqrs.common.enumeration.EventType;
import com.simplifysynergy.cqrs.common.event.UserCreateEvent;
import com.simplifysynergy.cqrs.common.event.UserDeleteEvent;
import com.simplifysynergy.cqrs.common.event.UserUpdateEvent;
import com.simplifysynergy.cqrs.common.util.AuditMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

@Component
@Slf4j
@AllArgsConstructor
public class AuditEventListenerHandler {

    private final AuditUseCase useCase;
    private final ObjectMapper objectMapper;
    private final CountDownLatch latch = new CountDownLatch(3);

    @KafkaListener(topics = "${spring.kafka.transferTopic}",  groupId = "${spring.kafka.groupId}")
    public Mono<Void> consumeAuditEvent(ConsumerRecord<String, String> consumerRecord) {
        try {
            String message = consumerRecord.value();
            Map<String, String> userEvent = new ObjectMapper().readValue(message, Map.class);

            if (userEvent.get("type").equals(EventType.CREATE.name())) {
                UserCreateEvent event = objectMapper.readValue(message, UserCreateEvent.class);
                User user = event.user();
                String payload = objectMapper.writeValueAsString(user);

                Audit audit = AuditMapper.mapUserToAudit(payload, event);
                return useCase.save(audit)
                        .mapNotNull(savedAudit -> {
                            log.info("Save audit {} in audit readonly db", savedAudit);
                            latch.countDown();
                            return savedAudit;
                        }).then();
            } else if (userEvent.get("type").equals(EventType.DELETE.name())) {
                UserDeleteEvent userDeleteEvent = objectMapper.readValue(message, UserDeleteEvent.class);
                User user = userDeleteEvent.user();
                return useCase.deleteById(user.getId());
            } else {
                UserUpdateEvent event = objectMapper.readValue(message, UserUpdateEvent.class);
                User user = event.user();
                String payload = objectMapper.writeValueAsString(user);

                Audit audit = AuditMapper.mapUserToAudit(payload, event);
                return useCase.update(audit)
                        .map(updatedAudit -> {
                            log.info("Updated audit {} in audit readonly db", updatedAudit);
                            latch.countDown();
                            return updatedAudit;
                        }).then();
            }
        } catch (Exception e) {
            log.error("Error while handling message", e);
            throw new KafkaException(e.getMessage(),e);
        }
    }


}
