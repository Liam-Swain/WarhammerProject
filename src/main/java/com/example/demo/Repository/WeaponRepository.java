package com.example.demo.Repository;

import com.example.demo.Documents.WeaponsDocumentModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WeaponRepository extends MongoRepository<WeaponsDocumentModel, String> {


    WeaponsDocumentModel save(WeaponsDocumentModel weapon);

    WeaponsDocumentModel findOneByWeaponName(String weaponName);

}
