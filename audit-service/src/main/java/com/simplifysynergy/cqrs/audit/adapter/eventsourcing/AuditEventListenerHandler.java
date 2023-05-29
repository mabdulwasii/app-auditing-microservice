package com.simplifysynergy.cqrs.audit.adapter.eventsourcing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.simplifysynergy.cqrs.audit.domain.entity.UserAudit;
import com.simplifysynergy.cqrs.audit.domain.mapper.AuditMapper;
import com.simplifysynergy.cqrs.audit.usecase.AuditUseCase;
;
import com.simplifysynergy.cqrs.common.Event;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@AllArgsConstructor
public class AuditEventListenerHandler {

    private final AuditUseCase auditQueryUseCase;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${spring.kafka.transferTopic}", groupId = "${spring.kafka.groupId}", containerFactory = "kafkaListenerContainerFactory")
    public Mono<UserAudit> consumeAuditEvent(Event event) {
        log.info("Consuming userAudit event {} ", event);
        objectMapper.registerModule(new Jdk8Module());
        String payload;
        try {
            payload = objectMapper.writeValueAsString(event.getUserDto());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not parse event user" + e.getMessage(), e);
        }
        UserAudit userAudit = AuditMapper.mapUserToAudit(event, payload);
            log.info("consumeAuditEvent userAudit to save {} ", userAudit);
            return auditQueryUseCase.save(userAudit);
    }
}