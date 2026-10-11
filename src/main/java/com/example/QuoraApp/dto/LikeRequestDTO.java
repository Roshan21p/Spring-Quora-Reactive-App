package com.example.QuoraApp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data 
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeRequestDTO {
    
    @NotBlank (message = "Target ID is required")
    private String targetId; // ID of the target (question or answer) to like

    @NotBlank (message = "Target Type is required")
    private String targetType; // Type of the target (e.g., "question" or "answer")

    @NotBlank (message = "Is Like is required")
    private Boolean isLike; // true for like, false for dislike
}
