package com.simplifysynergy.cqrs.entity.domain;

import com.simplifysynergy.cqrs.entity.enumeration.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Audit {

    @Id
    private String id = UUID.randomUUID().toString();
    private String payload;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String createdBy;
    private String modifiedBy;
    private EventType type;
}
