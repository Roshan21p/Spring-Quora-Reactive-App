package com.example.QuoraApp.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Data 
@Builder 
@NoArgsConstructor 
@AllArgsConstructor 
@Document (collection = "likes")
public class Like {
    
    @Id 
    private String id;

    private String targetId; // ID of the target (question or answer) that is liked

    private String targetType; // Type of the target (e.g., "question" or "answer")

    private Boolean isLike; 

    @CreatedDate 
    private LocalDateTime createdAt; 
}
