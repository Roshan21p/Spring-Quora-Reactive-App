package com.example.QuoraApp.repositories;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.QuoraApp.models.QuestionElasticDocument;

@Repository 
public interface QuestionDocumentRepository extends ElasticsearchRepository<QuestionElasticDocument, String> {
    
    // Work for only for one word search, for multiple words we need to use custom query
    //List<QuestionElasticDocument> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content);

    @Query ("""
    {
      "multi_match": {
        "query": "?0",
        "fields": ["title", "content"],
        "operator": "or"
      }
    }
    """)
    List<QuestionElasticDocument> searchByTitleOrContent(String query);
    
}
