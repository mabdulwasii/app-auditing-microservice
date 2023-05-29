package com.simplifysynergy.cqrs.common.domain;

import com.simplifysynergy.cqrs.common.enumeration.EventType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table("user_audit")
public class UserAudit {

    @Id
    private String id;
    @OneToOne(mappedBy = "id")
    private User user;
    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime modifiedDate = LocalDateTime.now();
    private String createdBy = "System";
    private String modifiedBy = "System";
    private EventType type;

}
