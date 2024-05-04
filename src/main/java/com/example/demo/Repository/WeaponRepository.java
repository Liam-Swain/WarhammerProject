package com.example.demo.Repository;

import com.example.demo.Models.WeaponsDocumentModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WeaponRepository extends MongoRepository<WeaponsDocumentModel, String> {


    WeaponsDocumentModel save(WeaponsDocumentModel weapon);


}