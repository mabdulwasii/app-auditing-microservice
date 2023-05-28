package com.simplifysynergy.cqrs.audit.adapter.repository;


import com.simplifysynergy.cqrs.common.domain.Audit;
import com.simplifysynergy.cqrs.common.enumeration.EventType;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

public interface AuditRepository extends ReactiveCrudRepository<Audit, String> {
    Flux<Audit> findAllByType(EventType type);
    Flux<Audit> findAllByTypeAndCreatedDateBetween(EventType type, LocalDateTime start, LocalDateTime end);
    Flux<Audit> findAllByCreatedDateBetween(LocalDateTime start, LocalDateTime end);
}
