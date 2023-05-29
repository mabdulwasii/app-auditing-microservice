package com.simplifysynergy.cqrs.audit.adapter.repository;


import com.simplifysynergy.cqrs.audit.domain.entity.UserAudit;
import com.simplifysynergy.cqrs.common.enumeration.EventType;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
public interface AuditRepository extends ReactiveCrudRepository<UserAudit, String> {
    Flux<UserAudit> findAllByType(EventType type);
    Flux<UserAudit> findAllByTypeAndCreatedDateBetween(EventType type, LocalDateTime start, LocalDateTime end);
    Flux<UserAudit> findAllByCreatedDateBetween(LocalDateTime start, LocalDateTime end);
}
