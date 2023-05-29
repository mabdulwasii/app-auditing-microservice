package com.simplifysynergy.cqrs.audit.domain.mapper;

import com.simplifysynergy.cqrs.audit.domain.entity.UserAudit;
import com.simplifysynergy.cqrs.common.Event;

import java.util.UUID;

public class AuditMapper {
    private AuditMapper() {
    }

    public static UserAudit mapUserToAudit(Event event, String payload) {
        return new UserAudit()
            .setId(UUID.randomUUID().toString())
            .setType(event.getType())
            .setCreatedBy(event.getCreatedBy())
            .setModifiedDate(event.getModifiedDate())
            .setModifiedBy(event.getModifiedBy())
            .setCreatedBy(event.getCreatedBy())
            .setPayload(payload);
    }
}
