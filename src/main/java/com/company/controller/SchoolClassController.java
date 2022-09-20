package com.company.controller;

import com.company.entity.SchoolClass;
import com.company.entity.Student;
import com.company.service.SchoolClassService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classes")
public class SchoolClassController {

    private SchoolClassService schoolClassService;

    public SchoolClassController(SchoolClassService schoolClassService) {
        this.schoolClassService = schoolClassService;
    }

    @GetMapping(path = "/all")
    public List<SchoolClass> getAllClasses(){
        return schoolClassService.getAllClasses();
    }

    @PostMapping
    public void createNewClass(@RequestBody SchoolClass schoolClass){
        schoolClassService.createNewClass(schoolClass);
    }

}
