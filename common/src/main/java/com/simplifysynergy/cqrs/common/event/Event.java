package com.simplifysynergy.cqrs.common.event;

import com.simplifysynergy.cqrs.common.domain.User;
import com.simplifysynergy.cqrs.common.enumeration.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Event {
    private User user;
    private EventType type;
    private LocalDateTime createdDate = LocalDateTime.now();
    private String createdBy = "System";
    private LocalDateTime modifiedDate = LocalDateTime.now();
    private String modifiedBy = "System";

    public Event(User user, EventType type) {
        this.user = user;
        this.type = type;
    }

    public LocalDateTime getModifiedDate() {
        if (EventType.UPDATE.equals(this.getType())) {
            return LocalDateTime.now();
        }else if (modifiedDate == null) {
            return createdDate;
        }
        return null;
    }
}
