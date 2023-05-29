package com.simplifysynergy.cqrs.command.adapter.eventsourcing;

import com.simplifysynergy.cqrs.command.adapter.eventsourcing.config.KafkaConfigProperties;
import com.simplifysynergy.cqrs.common.event.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserCommandEventHandler {

    private final KafkaTemplate<String, Event> kafkaTemplate;
    private final KafkaConfigProperties properties;

    public UserCommandEventHandler(KafkaTemplate<String, Event> kafkaTemplate, KafkaConfigProperties properties) {
        this.kafkaTemplate = kafkaTemplate;
        this.properties = properties;
    }

    public void publishEvent(Event event) {
        log.info("UserCommandEventHandler::publishEvent {} ", event);
        String userEventTopic = properties.getTransferTopic();
        kafkaTemplate.send(userEventTopic, event);
        log.info("Sent json {} to topic {} ", event, userEventTopic);
    }
}
