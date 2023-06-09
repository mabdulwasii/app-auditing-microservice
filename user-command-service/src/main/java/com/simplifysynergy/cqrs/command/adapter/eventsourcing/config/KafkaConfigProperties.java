package com.simplifysynergy.cqrs.command.adapter.eventsourcing.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.kafka")
public class KafkaConfigProperties {
    private String transferTopic;
    private String bootstrapServers;
    private String groupId;
    private int maxAttempts;

    public String getTransferTopic() {
        return transferTopic;
    }

    public void setTransferTopic(String transferTopic) {
        this.transferTopic = transferTopic;
    }

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }
}