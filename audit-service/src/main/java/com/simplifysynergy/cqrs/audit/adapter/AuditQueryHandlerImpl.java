package com.simplifysynergy.cqrs.audit.adapter;

import com.simplifysynergy.cqrs.audit.adapter.repository.AuditRepository;
import com.simplifysynergy.cqrs.audit.domain.entity.UserAudit;
import com.simplifysynergy.cqrs.audit.usecase.port.AuditQueryHandler;
import com.simplifysynergy.cqrs.common.enumeration.EventType;
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
    public Flux<UserAudit> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<UserAudit> findAuditById(String uuid) {
        return repository.findById(uuid);
    }

    @Override
    public Flux<UserAudit> findAuditBetween(LocalDateTime start, LocalDateTime end) {
        return repository.findAllByCreatedDateBetween(start, end);
    }

    @Override
    public Flux<UserAudit> findAuditByType(EventType type) {
        return repository.findAllByType(type);
    }

    @Override
    public Flux<UserAudit> findAuditByTypeBetween(EventType type, LocalDateTime start, LocalDateTime end) {
        return repository.findAllByTypeAndCreatedDateBetween(type, start, end);
    }

    @Override
    public Mono<UserAudit> save(UserAudit userAudit) {
        log.info("AuditQueryHandlerImpl::save attempting to save {} ", userAudit);
        return repository.save(userAudit);
    }
}
