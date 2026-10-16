package com.example.QuoraApp.producers;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.QuoraApp.config.KafkaConfig;

import com.example.QuoraApp.events.ViewCountEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service 
@RequiredArgsConstructor 
@Slf4j
public class KakfaEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishViewCountEvent(ViewCountEvent viewCountEvent) {
        // Publish the event to the Kafka topic
        kafkaTemplate.send(KafkaConfig.TOPIC_NAME, viewCountEvent.getTargetId(), viewCountEvent)
        .whenComplete((result, err) -> {
            if(err != null) {
                // Handle the error if the event publishing fails
                log.error("Failed to publish view count event for targetId: {}",viewCountEvent.getTargetId(),err);            
            } else {
                // Event published successfully
            log.info("View count event published successfully for targetId: {}",viewCountEvent.getTargetId());           
         }
        });
    }
    
}
