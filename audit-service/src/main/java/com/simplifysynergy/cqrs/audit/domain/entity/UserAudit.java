package com.simplifysynergy.cqrs.audit.domain.entity;

import com.simplifysynergy.cqrs.common.enumeration.EventType;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class UserAudit {
    @Id
    private String id;
    private String payload;
    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime modifiedDate = LocalDateTime.now();
    private String createdBy = "System";
    private String modifiedBy = "System";
    private EventType type;

    public String getId() {
        return id;
    }

    public UserAudit setId(String id) {
        this.id = id;
        return this;
    }

    public String getPayload() {
        return payload;
    }

    public UserAudit setPayload(String payload) {
        this.payload = payload;
        return this;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public UserAudit setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public UserAudit setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public UserAudit setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public UserAudit setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public EventType getType() {
        return type;
    }

    public UserAudit setType(EventType type) {
        this.type = type;
        return this;
    }

    @Override
    public String   toString() {
        return "UserAudit{" +
                "id='" + id + '\'' +
                ", payload='" + payload + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", createdBy='" + createdBy + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", type=" + type +
                '}';
    }
}
