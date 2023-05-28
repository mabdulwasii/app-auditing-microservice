package com.simplifysynergy.cqrs.common.event;

import com.simplifysynergy.cqrs.common.domain.User;

public record UserCreateEvent(User user) implements Event{
}
