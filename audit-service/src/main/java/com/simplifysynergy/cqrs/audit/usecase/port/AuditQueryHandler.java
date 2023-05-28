package com.simplifysynergy.cqrs.audit.usecase.port;

import com.simplifysynergy.cqrs.entity.domain.Audit;
import com.simplifysynergy.cqrs.entity.enumeration.EventType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface AuditQueryHandler {
    Flux<Audit> findAll();
    Mono<Audit> findAuditById(String uuid);
    Flux<Audit> findAuditBetween(LocalDateTime start, LocalDateTime end);
    Flux<Audit> findAuditByType(EventType type);
    Flux<Audit> findAuditByTypeBetween(EventType type, LocalDateTime start, LocalDateTime end);
    Mono<Audit> save(Audit audit);
}