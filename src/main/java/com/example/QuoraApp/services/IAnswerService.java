package com.example.QuoraApp.services;

import com.example.QuoraApp.dto.AnswerRequestDTO;
import com.example.QuoraApp.dto.AnswerResponseDTO;

import reactor.core.publisher.Mono;

public interface IAnswerService {

    public Mono<AnswerResponseDTO> createAnswer(AnswerRequestDTO answerRequestDTO);

    public Mono<AnswerResponseDTO> getAnswerById(String answerId);
    
}
