package com.simplifysynergy.cqrs.common.event;

import com.simplifysynergy.cqrs.common.enumeration.EventType;

import java.time.LocalDateTime;

public interface Event {
    default EventType getType() {
        return EventType.CREATE;
    }

    default LocalDateTime getCreatedDate() {
        return LocalDateTime.now();
    }

    default LocalDateTime getModifiedDate() {
        if (EventType.UPDATE.equals(this.getType())) {
            return LocalDateTime.now();
        }
        return null;
    }

    default String getCreatedBy() {
        return "System";
    }
    default String getModifiedBy() {
        return "System";
    }
}
