package com.example.QuoraApp.dto;

import java.time.LocalDateTime;

import lombok.*;

@Data 
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeResponseDTO {
    
    private String id;

    private String targetId; // ID of the target (question or answer) that was liked

    private String targetType; // Type of the target (e.g., "question" or "answer")

    private Boolean isLike; // true for like, false for dislike

    private LocalDateTime createdAt; // Timestamp when the like was created
}
