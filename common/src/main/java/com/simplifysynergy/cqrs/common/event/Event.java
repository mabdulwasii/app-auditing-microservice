package com.simplifysynergy.cqrs.common.event;

import com.simplifysynergy.cqrs.common.domain.User;
import com.simplifysynergy.cqrs.common.enumeration.EventType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Event {
    private User user;
    private EventType type;
    private LocalDateTime createdDate = LocalDateTime.now();
    private String createdBy = "System";
    private LocalDateTime modifiedDate = LocalDateTime.now();
    private String modifiedBy = "System";

    public LocalDateTime getModifiedDate() {
        if (EventType.UPDATE.equals(this.getType())) {
            return LocalDateTime.now();
        }else if (modifiedDate == null) {
            return createdDate;
        }
        return null;
    }
}
