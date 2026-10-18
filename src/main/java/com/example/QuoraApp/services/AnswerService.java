package com.example.QuoraApp.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.QuoraApp.adapter.AnswerAdapter;
import com.example.QuoraApp.dto.AnswerRequestDTO;
import com.example.QuoraApp.dto.AnswerResponseDTO;
import com.example.QuoraApp.models.Answer;
import com.example.QuoraApp.repositories.AnswerRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service 
@RequiredArgsConstructor 
public class AnswerService implements IAnswerService {
    
    private final AnswerRepository answerRepository;

    @Override
    public Mono<AnswerResponseDTO> createAnswer(AnswerRequestDTO answerRequestDTO) {
        // Implementation for creating an answer
        Answer answer = Answer.builder()
                .content(answerRequestDTO.getContent())
                .questionId(answerRequestDTO.getQuestionId())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        
        return answerRepository.save(answer)
                .map(AnswerAdapter::toAnswerResponseDTO);
    }

    @Override 
    public Mono<AnswerResponseDTO> getAnswerById(String id) {
        // Implementation for retrieving an answer by ID
        return answerRepository.findById(id)
                .map(AnswerAdapter::toAnswerResponseDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("Answer not found")));
    }

    @Override 
    public Mono<AnswerResponseDTO> updateAnswer(String id, AnswerRequestDTO answerRequestDTO) {
        // Implementation for updating an answer
        return answerRepository.findById(id)
                .flatMap(existingAnswer -> {
                    existingAnswer.setContent(answerRequestDTO.getContent());
                    existingAnswer.setUpdatedAt(LocalDateTime.now());
                    return answerRepository.save(existingAnswer);
                })
                .map(AnswerAdapter::toAnswerResponseDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("Answer not found")));
    }

    @Override 
    public Mono<Void> deleteAnswer(String id) {
        // Implementation for deleting an answer
        return answerRepository.findById(id)
                .flatMap(existingAnswer -> answerRepository.deleteById(id))
                .switchIfEmpty(Mono.error(new RuntimeException("Answer not found")));
    }

    @Override 

    public Flux<AnswerResponseDTO> getAllAnswers() {
        // Implementation for retrieving all answers
        return answerRepository.findAll()
                .map(AnswerAdapter::toAnswerResponseDTO);
    }

    @Override 
    public Flux<AnswerResponseDTO> getAnswersByQuestionId(String questionId) {
        // Implementation for retrieving answers by question ID
        return answerRepository.findByQuestionId(questionId)
                .map(AnswerAdapter::toAnswerResponseDTO);
    }


    @Override 
    public Mono<Long> getAnswerCountByQuestionId(String questionId) {
        // Implementation for counting answers by question ID
        return answerRepository.countByQuestionId(questionId);
    }

    @Override 
    public Flux<AnswerResponseDTO> getAnswersByQuestionIdOrderByCreatedAtDesc(String questionId) {
        // Implementation for retrieving answers by question ID ordered by creation date descending
        return answerRepository.findByQuestionIdOrderByCreatedAtDesc(questionId)
                .map(AnswerAdapter::toAnswerResponseDTO);
    }

    @Override 
    public Flux<AnswerResponseDTO> getAnswersByQuestionIdOrderByCreatedAtAsc(String questionId) {
        // Implementation for retrieving answers by question ID ordered by creation date ascending
        return answerRepository.findByQuestionIdOrderByCreatedAtAsc(questionId)
                .map(AnswerAdapter::toAnswerResponseDTO);
    }

}
