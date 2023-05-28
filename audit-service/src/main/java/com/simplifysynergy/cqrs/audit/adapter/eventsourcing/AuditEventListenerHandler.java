package com.simplifysynergy.cqrs.audit.adapter.eventsourcing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplifysynergy.cqrs.audit.usecase.AuditUseCase;
import com.simplifysynergy.cqrs.entity.domain.Audit;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@Slf4j
@AllArgsConstructor
public class AuditEventListenerHandler {

    private final AuditUseCase useCase;
    private final CountDownLatch latch = new CountDownLatch(3);

    @KafkaListener(topics = "${app.topic.audit}")
    public void consumeAuditEvent(ConsumerRecord<String, String> consumerRecord) throws Exception {
        Audit audit = new ObjectMapper().readValue(consumerRecord.value(), Audit.class);
        useCase.save(audit)
                .map(savedAudit -> {
                    log.info("Save audit {} in audit readonly db", audit);
                    latch.countDown();
                    return savedAudit;
                });

    }


}
