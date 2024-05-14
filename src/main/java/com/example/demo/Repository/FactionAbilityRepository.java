package com.example.demo.Repository;

import com.example.demo.Documents.FactionAbilityDocumentModel;
import com.example.demo.Models.FactionAbilityRequestModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactionAbilityRepository extends MongoRepository<FactionAbilityDocumentModel, String> {
    FactionAbilityDocumentModel save(FactionAbilityDocumentModel factionAbility);

    FactionAbilityDocumentModel findOneByFactionAbility(String factionAbility);
}
