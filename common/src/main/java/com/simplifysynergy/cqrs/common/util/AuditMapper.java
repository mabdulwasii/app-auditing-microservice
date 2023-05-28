package com.simplifysynergy.cqrs.common.util;

import com.simplifysynergy.cqrs.common.domain.Audit;
import com.simplifysynergy.cqrs.common.event.Event;

import java.util.UUID;

public class AuditMapper {
    private AuditMapper() {}

    public static Audit mapUserToAudit(String payload, Event event) {
       return new Audit().setId(UUID.randomUUID().toString())
                .setType(event.getType())
                .setPayload(payload)
                .setCreatedBy(event.getCreatedBy())
                .setModifiedDate(event.getModifiedDate())
                .setCreatedBy(event.getCreatedBy())
                .setModifiedDate(event.getModifiedDate());
    }

}
