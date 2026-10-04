package com.example.QuoraApp.services;

import java.time.LocalDateTime;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.QuoraApp.adapter.QuestionAdapter;
import com.example.QuoraApp.dto.QuestionRequestDTO;
import com.example.QuoraApp.dto.QuestionResponseDTO;
import com.example.QuoraApp.models.Question;
import com.example.QuoraApp.repositories.QuestionRepository;
import com.example.QuoraApp.utils.CursorUtils;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO) {

        Question question = Question.builder()
                .title(questionRequestDTO.getTitle())
                .content(questionRequestDTO.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return questionRepository.save(question)
                .map(QuestionAdapter::toQuestionResponseDTO)
                .doOnSuccess(response -> System.out.println("Question created successfully: " + response))
                .doOnError(error -> System.err.println("Error creating question: " + error.getMessage()));
    }

    @Override
    public Flux<QuestionResponseDTO> searchQuestions(String searchTerm, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return questionRepository.findByTitleOrContentContainingIgnoreCase(searchTerm, pageable)
                .map(QuestionAdapter::toQuestionResponseDTO)
                .doOnComplete(() -> System.out.println("Questions searched successfully"))
                .doOnError(error -> System.err.println("Error searching questions: " + error.getMessage()));
    }

    @Override
    public Flux<QuestionResponseDTO> getQuestionsAfterCursor(String cursor, int size) {

        Pageable pageable = PageRequest.of(0, size);

        if(!CursorUtils.isValidCursor(cursor)) {
            return questionRepository.findTop10ByOrderByCreatedAtAsc(pageable)
                    .map(QuestionAdapter::toQuestionResponseDTO)
                    .doOnComplete(() -> System.out.println("Questions retrieved successfully without cursor"))
                    .doOnError(error -> System.err.println("Error retrieving questions without cursor: " + error.getMessage()));
        } else {
            LocalDateTime cursorDateTime = CursorUtils.parseCursor(cursor);

            return questionRepository.findByCreatedAtGreaterThanOrderByCreatedAtAsc(cursorDateTime, pageable)
                    .map(QuestionAdapter::toQuestionResponseDTO)
                    .doOnComplete(() -> System.out.println("Questions retrieved successfully after cursor: " + cursor))
                    .doOnError(error -> System.err.println("Error retrieving questions after cursor: " + error.getMessage()));
        }
    }
}
