package com.simplifysynergy.cqrs.entity.event;

import com.simplifysynergy.cqrs.entity.enumeration.EventType;

import java.time.LocalDateTime;

public interface Event {
    default EventType getType() {
        return EventType.CREATE;
    }

    default LocalDateTime createdDate() {
        return LocalDateTime.now();
    }

    default LocalDateTime modifiedDate() {
        if (EventType.UPDATE.equals(this.getType())) {
            return LocalDateTime.now();
        }
        return null;
    }

    default String createdBy() {
        return "system";
    }
}
