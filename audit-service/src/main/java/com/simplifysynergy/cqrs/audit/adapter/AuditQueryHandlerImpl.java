package com.simplifysynergy.cqrs.audit.adapter;

import com.simplifysynergy.cqrs.audit.adapter.repository.AuditRepository;
import com.simplifysynergy.cqrs.audit.usecase.port.AuditQueryHandler;
import com.simplifysynergy.cqrs.entity.domain.Audit;
import com.simplifysynergy.cqrs.entity.enumeration.EventType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
@Slf4j
public class AuditQueryHandlerImpl implements AuditQueryHandler {

    private final AuditRepository repository;

    @Override
    public Flux<Audit> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Audit> findAuditById(String uuid) {
        return repository.findById(uuid);
    }

    @Override
    public Flux<Audit> findAuditBetween(LocalDateTime start, LocalDateTime end) {
        return repository.findAllByCreatedDateBetween(start, end);
    }

    @Override
    public Flux<Audit> findAuditByType(EventType type) {
        return repository.findAllByType(type);
    }

    @Override
    public Flux<Audit> findAuditByTypeBetween(EventType type, LocalDateTime start, LocalDateTime end) {
        return repository.findAllByTypeAndCreatedDateBetween(type, start, end);
    }

    @Override
    public Mono<Audit> save(Audit audit) {
        return repository.save(audit);
    }
}
