package com.example.QuoraApp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuoraApp.dto.QuestionRequestDTO;
import com.example.QuoraApp.dto.QuestionResponseDTO;
import com.example.QuoraApp.services.QuestionService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController 
@RequestMapping ("/api/v1/questions")
@RequiredArgsConstructor 
public class QuestionController {
    
    private final QuestionService questionService;

    @PostMapping ()
    public Mono<QuestionResponseDTO> createQuestion(@RequestBody QuestionRequestDTO questionRequestDTO) {
        return questionService.createQuestion(questionRequestDTO)
        .doOnSuccess(response -> System.out.println("Question created successfully: " + response))
        .doOnError(error -> System.out.println("Error creating question: " + error));
    }

    @GetMapping ("/search")
    public Flux<QuestionResponseDTO> searchQuestions(@RequestParam String query, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return questionService.searchQuestions(query, page, size);
    }

    @GetMapping ("/cursor")
    public Flux<QuestionResponseDTO> getQuestionsAfterCursor(@RequestParam(required = false) String cursor, @RequestParam(defaultValue = "10") int size) {
        return questionService.getQuestionsAfterCursor(cursor, size)
        .doOnComplete(() -> System.out.println("Questions retrieved successfully after cursor" + (cursor != null ? ": " + cursor : " without cursor")))
        .doOnError(error -> System.err.println("Error retrieving questions after cursor: " + error.getMessage()));
    }

}

