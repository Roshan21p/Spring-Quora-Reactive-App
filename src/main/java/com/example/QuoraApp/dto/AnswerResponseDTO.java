package com.example.QuoraApp.dto;

import java.time.LocalDateTime;

import lombok.*;

@Data 
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponseDTO {
    
    private String id;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
