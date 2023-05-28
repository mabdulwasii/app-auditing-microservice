package com.simplifysynergy.cqrs.common.event;

import com.simplifysynergy.cqrs.common.domain.User;
import com.simplifysynergy.cqrs.common.enumeration.EventType;

public record UserDeleteEvent(User user) implements Event{
    @Override
    public EventType getType() {
        return EventType.DELETE;
    }
}
