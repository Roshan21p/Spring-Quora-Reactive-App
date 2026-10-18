package com.example.QuoraApp.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.QuoraApp.repositories.QuestionRepository;

import com.example.QuoraApp.config.KafkaConfig;

import com.example.QuoraApp.events.ViewCountEvent;

@Service 
@RequiredArgsConstructor 
@Slf4j 
public class KakfaEventConsumer {
    
    private final QuestionRepository questionRepository;

    @KafkaListener(
        topics = KafkaConfig.TOPIC_NAME,
        groupId = "view-count-consumer",
        containerFactory = "kafkaListenerContainerFactory"
    )

    public void handleViewCountEvent(ViewCountEvent viewCountEvent) {

        questionRepository.findById(viewCountEvent.getTargetId())
        .flatMap(question -> {
            log.info("Received view count event for targetId: {}", viewCountEvent.getTargetId());
            Integer views = question.getViews();
            question.setViews(views == null ? 0 : views + 1);
            return questionRepository.save(question);
        })
        .subscribe(updateQuestion -> {
            log.info("Incremented view count for targetId: {}. New view count: {}", updateQuestion.getId(), updateQuestion.getViews());
        }, error -> {
            log.error("Error processing view count event for targetId: {}", viewCountEvent.getTargetId(), error.getMessage());
        });
    }
}
