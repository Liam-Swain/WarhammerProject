package com.example.demo.ServiceImpl;

import com.example.demo.Models.FormModel;
import com.example.demo.Models.Stats;
import com.example.demo.Models.TestModel;
import com.example.demo.Repository.ProjectRepository;
import com.example.demo.Repository.StatRepository;
import com.example.demo.Repository.TestModelRepository;
import com.example.demo.Service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {



    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    StatRepository statRepository;

    @Autowired
    TestModelRepository testModelRepository;

    @Override
    public void UploadForm(FormModel form) {
        projectRepository.save(form);
    }

    @Override
    public void UploadTest(TestModel model){
        testModelRepository.save(model);
    }

    @Override
    public Stats GetStats(){
        List<Stats> statList = statRepository.findAll();
        return statList.get(0);
    }
}
