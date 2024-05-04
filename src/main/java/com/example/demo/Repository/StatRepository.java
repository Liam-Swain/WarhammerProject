package com.example.demo.Repository;


import com.example.demo.Models.Stats;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface StatRepository extends MongoRepository<Stats, String> {


    Stats save(Stats statModel);
    void deleteAll();
}
