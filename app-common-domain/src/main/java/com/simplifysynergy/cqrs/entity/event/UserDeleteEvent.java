package com.simplifysynergy.cqrs.entity.event;

import com.simplifysynergy.cqrs.entity.enumeration.EventType;

public class UserDeleteEvent implements Event{
    @Override
    public EventType getType() {
        return EventType.DELETE;
    }
}
