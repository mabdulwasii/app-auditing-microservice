package com.simplifysynergy.cqrs.common.util;

import com.simplifysynergy.cqrs.common.domain.User;
import com.simplifysynergy.cqrs.common.domain.UserAudit;
import com.simplifysynergy.cqrs.common.event.Event;

import java.util.UUID;

public class AuditMapper {
    private AuditMapper() {}

    public static UserAudit mapUserToAudit(Event event) {
       return UserAudit.builder()
               .id(UUID.randomUUID().toString())
                .type(event.getType())
                .createdBy(event.getCreatedBy())
                .createdDate(event.getCreatedDate())
                .modifiedBy(event.getModifiedBy())
                .modifiedDate(event.getModifiedDate())
                .user(mapToSqlUser(event.getUser()))
               .build();
    }

    public static User mapToSqlUser(User user) {
        return  User.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .age(user.getAge())
                .dateOfBirth(user.getDateOfBirth())
                .build();
    }



}
