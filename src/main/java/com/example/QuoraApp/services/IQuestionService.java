package com.example.QuoraApp.services;

import com.example.QuoraApp.dto.QuestionRequestDTO;
import com.example.QuoraApp.dto.QuestionResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IQuestionService {
    
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO);

    /**
     * Search for questions based on a query.
     * @param query The search query.
     * @param page The page number.
     * @param size The number of questions to retrieve.
     * @return A Flux of QuestionResponseDTO.
     */
    public Flux<QuestionResponseDTO> searchQuestions(String query, int page, int size);

    /**
     * Get questions after a given cursor.
     * @param cursor The cursor to start from.
     * @param size The number of questions to retrieve.
     * @return A Flux of QuestionResponseDTO.
     */
    public Flux<QuestionResponseDTO> getQuestionsAfterCursor(String cursor, int size);
}


