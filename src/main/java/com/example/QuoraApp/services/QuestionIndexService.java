package com.example.QuoraApp.services;

import org.springframework.stereotype.Service;

import com.example.QuoraApp.models.Question;
import com.example.QuoraApp.models.QuestionElasticDocument;
import com.example.QuoraApp.repositories.QuestionDocumentRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class QuestionIndexService implements IQuestionIndexService {
    
    private final QuestionDocumentRepository questionDocumentRepository;

    @Override
    public void createQuestionIndex(Question question) {
        // Implementation for creating a question index in Elasticsearch
        // This could involve mapping the Question object to a QuestionElasticDocument
        // and saving it using the QuestionDocumentRepository.

        QuestionElasticDocument questionElasticDocument = QuestionElasticDocument.builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .build();
        
        // Save the document to Elasticsearch using the repository
        questionDocumentRepository.save(questionElasticDocument);
    }
    
}
