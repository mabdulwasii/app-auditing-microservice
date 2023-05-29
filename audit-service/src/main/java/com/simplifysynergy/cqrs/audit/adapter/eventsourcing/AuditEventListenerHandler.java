package com.simplifysynergy.cqrs.audit.adapter.eventsourcing;

import com.simplifysynergy.cqrs.audit.adapter.repository.AuditRepository;
import com.simplifysynergy.cqrs.audit.domain.entity.UserAudit;
import com.simplifysynergy.cqrs.audit.domain.mapper.AuditMapper;
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

    private final AuditRepository repository;

    @KafkaListener(topics = "${spring.kafka.transferTopic}", groupId = "${spring.kafka.groupId}", containerFactory = "kafkaListenerContainerFactory")
    public Mono<UserAudit> consumeAuditEvent(Event event) {
        log.info("Consuming userAudit event {} ", event);
            UserAudit userAudit = AuditMapper.mapUserToAudit(event);
            log.info("consumeAuditEvent userAudit to save {} ", userAudit);
            return repository.save(userAudit);
    }
}