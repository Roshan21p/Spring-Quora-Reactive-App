package com.example.QuoraApp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuoraApp.dto.AnswerRequestDTO;
import com.example.QuoraApp.dto.AnswerResponseDTO;
import com.example.QuoraApp.services.AnswerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController 
@RequestMapping ("/api/v1/answers")
@RequiredArgsConstructor 
public class AnswerController {
    
    private final AnswerService answerService;

    @PostMapping 
    public Mono<ResponseEntity<AnswerResponseDTO>> createAnswer(@Valid @RequestBody AnswerRequestDTO answerRequestDTO) {
        return answerService.createAnswer(answerRequestDTO)
                .map(answerResponseDTO -> ResponseEntity.status(HttpStatus.CREATED).body(answerResponseDTO))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build()));
    }

    @GetMapping ("/{id}")
    public Mono<ResponseEntity<AnswerResponseDTO>> getAnswerById(@PathVariable ("id") String id) {
        return answerService.getAnswerById(id)
                .map(answerResponseDTO -> ResponseEntity.ok().body(answerResponseDTO))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    @PutMapping ("/{id}")
    public Mono<ResponseEntity<AnswerResponseDTO>> updateAnswer(@PathVariable ("id") String id, @Valid @RequestBody AnswerRequestDTO answerRequestDTO) {
        return answerService.updateAnswer(id, answerRequestDTO)
                .map(answerResponseDTO -> ResponseEntity.ok().body(answerResponseDTO))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    @DeleteMapping ("/{id}")
    public Mono<ResponseEntity<Void>> deleteAnswer(@PathVariable ("id") String id) {
        return answerService.deleteAnswer(id)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    @GetMapping 
    public Flux<AnswerResponseDTO> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    @GetMapping ("/question/{questionId}")
    public Flux<AnswerResponseDTO> getAnswersByQuestionId(@PathVariable ("questionId") String questionId) {
        return answerService.getAnswersByQuestionId(questionId);
    }

    @GetMapping ("/question/{questionId}/count")
    public Mono<ResponseEntity<Long>> getAnswerCountByQuestionId(@PathVariable ("questionId") String questionId) {
        return answerService.getAnswerCountByQuestionId(questionId)
                .map(count -> ResponseEntity.ok().body(count))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    @GetMapping ("/question/{questionId}/desc")
    public Flux<AnswerResponseDTO> getAnswersByQuestionIdOrderByCreatedAtDesc(@PathVariable ("questionId") String questionId) {
        return answerService.getAnswersByQuestionIdOrderByCreatedAtDesc(questionId);
    }

    @GetMapping ("/question/{questionId}/asc")
    public Flux<AnswerResponseDTO> getAnswersByQuestionIdOrderByCreatedAtAsc(@PathVariable ("questionId") String questionId) {
        return answerService.getAnswersByQuestionIdOrderByCreatedAtAsc(questionId);
    }
}
