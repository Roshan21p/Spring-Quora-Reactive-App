package com.example.QuoraApp.adapter;

import com.example.QuoraApp.dto.AnswerResponseDTO;
import com.example.QuoraApp.models.Answer;
import com.example.QuoraApp.models.Question;

public class AnswerAdapter {
    
    public static AnswerResponseDTO toAnswerResponseDTO(Answer answer) {
        return AnswerResponseDTO.builder()
                .id(answer.getId())
                .content(answer.getContent())
                .questionId(answer.getQuestionId())
                .createdAt(answer.getCreatedAt())
                .updatedAt(answer.getUpdatedAt())
                .build();
    }
}
