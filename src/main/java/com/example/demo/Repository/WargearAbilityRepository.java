package com.example.demo.Repository;

import com.example.demo.Documents.WargearAbilityDocumentModel;
import com.example.demo.Models.WargearAbilityRequestModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WargearAbilityRepository extends MongoRepository<WargearAbilityDocumentModel, String> {
    WargearAbilityDocumentModel save(WargearAbilityDocumentModel wargearAbilityDocumentModel);

    WargearAbilityDocumentModel findOneByWargearAbilityName(String wargearAbilityName);
}
