package com.simplifysynergy.cqrs.audit.adapter.eventsourcing;

import com.simplifysynergy.cqrs.audit.usecase.AuditUseCase;
import com.simplifysynergy.cqrs.common.domain.UserAudit;
import com.simplifysynergy.cqrs.common.event.Event;
import com.simplifysynergy.cqrs.common.util.AuditMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@AllArgsConstructor
public class AuditEventListenerHandler {

    private final AuditUseCase useCase;

    @KafkaListener(topics = "${spring.kafka.transferTopic}", groupId = "${spring.kafka.groupId}", containerFactory = "kafkaListenerContainerFactory")
    public Mono<Void> consumeAuditEvent(Event event) {
        log.info("Consuming userAudit event {} ", event);
            User user = event.getUser();
            log.info("consumeAuditEvent user {} ", user);
            UserAudit userAudit = AuditMapper.mapUserToAudit(event);
            log.info("consumeAuditEvent userAudit to save {} ", userAudit);
            return useCase.save(userAudit)
                .map(savedAudit -> {
                    log.info("consumeAuditEvent::Saved userAudit {} in userAudit readonly db", savedAudit);
                    return savedAudit;
                }).then();
    }
}