package com.simplifysynergy.cqrs.audit.domain.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplifysynergy.cqrs.audit.domain.entity.UserAudit;
import com.simplifysynergy.cqrs.common.Event;
import com.simplifysynergy.cqrs.common.UserDto;

import java.util.UUID;

public class AuditMapper {
    private AuditMapper() {
    }

    public static UserAudit mapUserToAudit(Event event) {
        return UserAudit.builder()
            .id(UUID.randomUUID().toString())
            .type(event.getType())
            .createdBy(event.getCreatedBy())
            .createdDate(event.getCreatedDate())
            .modifiedBy(event.getModifiedBy())
            .modifiedDate(event.getModifiedDate())
            .payload(convertUserDtoToJson(event.getUserDto()))
            .build();
    }

    private static String convertUserDtoToJson(UserDto userDto) {
        try {
            return new ObjectMapper().writeValueAsString(userDto);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
