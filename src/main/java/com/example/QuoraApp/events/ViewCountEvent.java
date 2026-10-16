package com.example.QuoraApp.events;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Service 
@Getter
@AllArgsConstructor
@NoArgsConstructor 
public class ViewCountEvent {
    
    private String targetId;
    private String targetType;
    private LocalDateTime timestamp;
}
