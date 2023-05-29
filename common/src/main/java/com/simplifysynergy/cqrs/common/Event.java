package com.simplifysynergy.cqrs.common;

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
    private UserDto userDto;
    private EventType type;
    private LocalDateTime createdDate = LocalDateTime.now();
    private String createdBy = "System";
    private LocalDateTime modifiedDate = LocalDateTime.now();
    private String modifiedBy = "System";

    public Event(UserDto userDto, EventType eventType) {
        this.userDto = userDto;
        this.type = eventType;
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
