package com.simplifysynergy.cqrs.audit.adapter.repository;


import com.simplifysynergy.cqrs.common.domain.UserAudit;
import com.simplifysynergy.cqrs.common.enumeration.EventType;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
public interface AuditRepository extends R2dbcRepository<UserAudit, String> {
    Flux<UserAudit> findAllByType(EventType type);
    Flux<UserAudit> findAllByTypeAndCreatedDateBetween(EventType type, LocalDateTime start, LocalDateTime end);
    Flux<UserAudit> findAllByCreatedDateBetween(LocalDateTime start, LocalDateTime end);
}
