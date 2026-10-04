package com.example.QuoraApp.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.QuoraApp.models.Question;

@Repository
public interface QuestionRepository extends ReactiveMongoRepository<Question, String> {
    
}
