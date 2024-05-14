package com.example.demo.Repository;

import com.example.demo.Documents.UnitAbilityDocumentModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitAbilityRepository extends MongoRepository<UnitAbilityDocumentModel, String> {
    UnitAbilityDocumentModel save(UnitAbilityDocumentModel unitAbilityDocumentModel);

    UnitAbilityDocumentModel findOneByAbilityName(String unitAbilityName);
}
