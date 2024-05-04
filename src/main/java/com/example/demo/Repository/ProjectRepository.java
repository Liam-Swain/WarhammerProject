package com.example.demo.Repository;

import com.example.demo.Models.FormModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends MongoRepository<FormModel, String> {



    FormModel save(FormModel form);

    List<FormModel> findAll();
}
