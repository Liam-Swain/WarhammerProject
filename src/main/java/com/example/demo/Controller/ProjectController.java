package com.example.demo.Controller;

import com.example.demo.Models.FormModel;
import com.example.demo.Models.Stats;
import com.example.demo.Models.TestModel;
import com.example.demo.Service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("projectController")
@CrossOrigin
@Slf4j
public class ProjectController {


    @Autowired
    ProjectService service;

    @PostMapping("/formUpload")
    public void UploadForm(@RequestBody FormModel form){
        log.info("Uploading Form");
        service.UploadForm(form);
    }

    @PostMapping("/testUpload")
    public void UploadTest(@RequestBody TestModel model){
        log.info("Uploading Test Model");
        service.UploadTest(model);
    }

    @GetMapping("/stats")
    public Stats statsModel(){
        log.info("Getting Stats");
        return service.GetStats();
    }

}
