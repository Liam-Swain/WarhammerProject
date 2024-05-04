package com.example.demo.Service;

import com.example.demo.Models.FormModel;
import com.example.demo.Models.Stats;
import com.example.demo.Models.TestModel;

public interface ProjectService {



    public void UploadForm(FormModel form);

    public void UploadTest(TestModel model);

    public Stats GetStats();


}
