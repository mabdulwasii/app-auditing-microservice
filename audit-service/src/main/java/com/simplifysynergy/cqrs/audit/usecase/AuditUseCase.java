package com.simplifysynergy.cqrs.audit.usecase;

import com.simplifysynergy.cqrs.audit.usecase.port.AuditQueryHandler;
import com.simplifysynergy.cqrs.common.domain.Audit;
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

    public Flux<Audit> findAll() {
        return queryHandler.findAll();
    }

    public Mono<Audit> findAuditById(String uuid){
        return queryHandler.findAuditById(uuid);
    }

    public Flux<Audit> findAuditBetween(LocalDateTime start, LocalDateTime end){
        return queryHandler.findAuditBetween(start, end);
    }

    public Flux<Audit> findAuditByType(EventType type) {
        return queryHandler.findAuditByType(type);
    }

    public Flux<Audit> findAuditByTypeBetween(EventType type, LocalDateTime start, LocalDateTime end) {
        return queryHandler.findAuditByTypeBetween(type, start, end);
    }

    public Mono<Audit> save(Audit audit) {
        log.info("AuditQueryHandler::save attempting to save {} ", audit);
        return queryHandler.save(audit);
    }

    public Mono<Void> deleteById(String uuid) {
        return queryHandler.deleteById(uuid);
    }

    public Mono<Audit> update(Audit audit) {
        return queryHandler.update(audit);
    }
}
