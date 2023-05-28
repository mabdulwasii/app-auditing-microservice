package com.simplifysynergy.cqrs.command.adapter.eventsourcing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplifysynergy.cqrs.common.event.Event;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class UserCommandEventHandler {

    @Value(value = "${app.topic.user}")
    private String userEventTopic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public UserCommandEventHandler(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public Mono<Void> publishEvent(Event event) throws JsonProcessingException {
        try {
            val userJson = new ObjectMapper().writeValueAsString(event);
            log.info("Send json {} to topic {} ", userJson, userEventTopic);
            kafkaTemplate.send(userEventTopic, userJson);
            return Mono.empty();
        } catch (Exception e) {
            log.error("Error while publishing user event", e);
            throw new KafkaException("Error while publishing user event " + e.getMessage(), e);
        }
    }
}
