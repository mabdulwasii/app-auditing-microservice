package com.simplifysynergy.cqrs.audit.usecase.port;

import com.simplifysynergy.cqrs.audit.domain.entity.UserAudit;
import com.simplifysynergy.cqrs.common.enumeration.EventType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface AuditQueryHandler {
    Flux<UserAudit> findAll();
    Mono<UserAudit> findAuditById(String uuid);
    Flux<UserAudit> findAuditBetween(LocalDateTime start, LocalDateTime end);
    Flux<UserAudit> findAuditByType(EventType type);
    Flux<UserAudit> findAuditByTypeBetween(EventType type, LocalDateTime start, LocalDateTime end);
    Mono<UserAudit> save(UserAudit userAudit);
}
