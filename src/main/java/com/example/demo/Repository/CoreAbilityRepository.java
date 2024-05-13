package com.example.demo.Repository;



import com.example.demo.Documents.CoreAbilityDocumentModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoreAbilityRepository extends MongoRepository<CoreAbilityDocumentModel, String> {

    CoreAbilityDocumentModel save(CoreAbilityDocumentModel coreAbility);

    CoreAbilityDocumentModel findOneByName(String name);
}
