package com.simplifysynergy.cqrs.audit.domain.entity;

import com.simplifysynergy.cqrs.common.enumeration.EventType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
@Table("user_audit")
public class UserAudit {

    @Id
    private String id;
    private String payload;
    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime modifiedDate = LocalDateTime.now();
    private String createdBy = "System";
    private String modifiedBy = "System";
    private EventType type;

}
