package com.example.QuoraApp.services;

import com.example.QuoraApp.dto.AnswerResponseDTO;
import com.example.QuoraApp.dto.LikeRequestDTO;

import reactor.core.publisher.Mono;

public interface ILikeService {
    
    Mono<AnswerResponseDTO> createLike(LikeRequestDTO likeRequestDTO);

    Mono<AnswerResponseDTO> countLikesByTargetIdAndTargetType(String targetId, String targetType);

    Mono<AnswerResponseDTO> countDislikesByTargetIdAndTargetType(String targetId, String targetType);

    Mono<AnswerResponseDTO> toggleLike(String targetId, String targetType, Boolean isLike);
}
