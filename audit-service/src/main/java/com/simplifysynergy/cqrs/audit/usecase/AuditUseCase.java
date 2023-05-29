package com.simplifysynergy.cqrs.audit.usecase;

import com.simplifysynergy.cqrs.audit.usecase.port.AuditQueryHandler;
import com.simplifysynergy.cqrs.common.domain.UserAudit;
import com.simplifysynergy.cqrs.common.enumeration.EventType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class AuditUseCase {
    private final AuditQueryHandler queryHandler;

    public Flux<UserAudit> findAll() {
        return queryHandler.findAll();
    }

    public Mono<UserAudit> findAuditById(String uuid){
        return queryHandler.findAuditById(uuid);
    }

    public Flux<UserAudit> findAuditBetween(LocalDateTime start, LocalDateTime end){
        return queryHandler.findAuditBetween(start, end);
    }

    public Flux<UserAudit> findAuditByType(EventType type) {
        return queryHandler.findAuditByType(type);
    }

    public Flux<UserAudit> findAuditByTypeBetween(EventType type, LocalDateTime start, LocalDateTime end) {
        return queryHandler.findAuditByTypeBetween(type, start, end);
    }

    public Mono<UserAudit> save(UserAudit userAudit) {
        log.info("AuditQueryHandler::save attempting to save {} ", userAudit);
        return queryHandler.save(userAudit)
                .map(savedAudit -> {
                    log.info("AuditUseCase::saved UserAudit {} ", savedAudit);
                    return savedAudit;
                });
    }
}
