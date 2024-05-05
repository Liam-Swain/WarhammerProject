package com.example.demo.Repository;


import com.example.demo.Documents.WeaponsDocumentTypes;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WeaponTypeRepository extends MongoRepository<WeaponsDocumentTypes, String> {


    WeaponsDocumentTypes save(WeaponsDocumentTypes weaponRequestTypes);

    WeaponsDocumentTypes findByName(String name);

}
