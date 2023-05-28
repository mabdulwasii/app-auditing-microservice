package com.simplifysynergy.cqrs.common.domain;

import com.simplifysynergy.cqrs.common.enumeration.EventType;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@ToString
@Document
public class Audit {

    @Id
    private String id;
    private String payload;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String createdBy;
    private String modifiedBy;
    private EventType type;

    public String getId() {
        return id;
    }

    public Audit setId(String id) {
        this.id = id;
        return this;
    }

    public String getPayload() {
        return payload;
    }

    public Audit setPayload(String payload) {
        this.payload = payload;
        return this;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public Audit setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public Audit setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Audit setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public Audit setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public EventType getType() {
        return type;
    }

    public Audit setType(EventType type) {
        this.type = type;
        return this;
    }
}
