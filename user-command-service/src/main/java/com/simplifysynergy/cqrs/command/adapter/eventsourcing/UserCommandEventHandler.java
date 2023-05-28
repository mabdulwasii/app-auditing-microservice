package com.simplifysynergy.cqrs.command.adapter.eventsourcing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.simplifysynergy.cqrs.command.adapter.eventsourcing.config.KafkaConfigProperties;
import com.simplifysynergy.cqrs.common.event.Event;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserCommandEventHandler {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaConfigProperties properties;

    public UserCommandEventHandler(KafkaTemplate<String, String> kafkaTemplate, KafkaConfigProperties properties) {
        this.kafkaTemplate = kafkaTemplate;
        this.properties = properties;
    }

    public void publishEvent(Event event) {
        log.info("UserCommandEventHandler::publishEvent {} ", event);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            val userJson = objectMapper.writeValueAsString(event);
            String userEventTopic = properties.getTransferTopic();
            kafkaTemplate.send(userEventTopic, userJson);
            log.info("Sent json {} to topic {} ", userJson, userEventTopic);
        } catch (Exception e) {
            log.error("Error while publishing user event", e);
            throw new KafkaException("Error while publishing user event " + e.getMessage(), e);
        }
    }
}
