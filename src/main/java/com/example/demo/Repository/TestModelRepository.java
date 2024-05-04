package com.example.demo.Repository;

import com.example.demo.Models.FormModel;
import com.example.demo.Models.TestModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TestModelRepository extends MongoRepository<TestModel, String> {


    List<TestModel> findByFirstName(String firstName);

    TestModel save(TestModel model);


}
