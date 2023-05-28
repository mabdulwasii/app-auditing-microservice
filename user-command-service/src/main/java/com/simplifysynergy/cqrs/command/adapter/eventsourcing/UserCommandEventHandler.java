package com.simplifysynergy.cqrs.command.adapter.eventsourcing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.simplifysynergy.cqrs.command.adapter.eventsourcing.config.KafkaConfigProperties;
import com.simplifysynergy.cqrs.common.event.Event;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class UserCommandEventHandler {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaConfigProperties properties;

    public void publishEvent(Event event) throws JsonProcessingException {
        log.info("UserCommandEventHandler::publishEvent {} ", event);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            val userJson = objectMapper.writeValueAsString(event);
            String userEventTopic = properties.getTransferTopic();
            log.info("Send json {} to topic {} ", userJson, userEventTopic);
            kafkaTemplate.send(userEventTopic, userJson);
        } catch (Exception e) {
            log.error("Error while publishing user event", e);
            throw new KafkaException("Error while publishing user event " + e.getMessage(), e);
        }
    }
}
