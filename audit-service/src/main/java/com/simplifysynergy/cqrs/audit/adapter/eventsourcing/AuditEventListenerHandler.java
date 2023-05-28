package com.simplifysynergy.cqrs.audit.adapter.eventsourcing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplifysynergy.cqrs.audit.usecase.AuditUseCase;
import com.simplifysynergy.cqrs.common.domain.Audit;
import com.simplifysynergy.cqrs.common.domain.User;
import com.simplifysynergy.cqrs.common.event.Event;
import com.simplifysynergy.cqrs.common.util.AuditMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@AllArgsConstructor
public class AuditEventListenerHandler {

    private final AuditUseCase useCase;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${spring.kafka.transferTopic}", groupId = "${spring.kafka.groupId}", containerFactory = "kafkaListenerContainerFactory")
    public Mono<Void> consumeAuditEvent(ConsumerRecord<String, String> consumerRecord) {
        log.info("Consuming audit event {} ", consumerRecord);
        try {
            String message = consumerRecord.value();
            Event event = objectMapper.readValue(message, Event.class);
            User user = event.getUser();
            log.info("consumeAuditEvent user {} ", user);
            String payload = objectMapper.writeValueAsString(user);
            Audit audit = AuditMapper.mapUserToAudit(payload, event);
            log.info("consumeAuditEvent audit to save {} ", audit);
            return useCase.save(audit)
                .map(savedAudit -> {
                    log.info("Saved audit {} in audit readonly db", savedAudit);
                    return savedAudit;
                }).then();
        }catch (Exception e) {
            log.error("Error while handling message", e);
            throw new KafkaException(e.getMessage(), e);
        }
    }
}