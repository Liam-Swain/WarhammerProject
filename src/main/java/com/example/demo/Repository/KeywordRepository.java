package com.example.demo.Repository;

import com.example.demo.Documents.KeywordDocumentModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends MongoRepository<KeywordDocumentModel, String> {
    KeywordDocumentModel save(KeywordDocumentModel keywordModel);

    KeywordDocumentModel findOneByKeyword(String keyword);
}
