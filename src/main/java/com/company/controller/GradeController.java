package com.company.controller;

import com.company.service.GradeService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/students/grades")
public class GradeController {

    private GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping(path = "/add")
    public void addNewGrade(
            @RequestParam(value = "studentId") Long studentId,
            @RequestParam(value = "subjectId") Long subjectId,
            @RequestParam(value = "grade") Integer gradeValue,
            @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        gradeService.addNewGrade(studentId,subjectId,gradeValue, date);
    }

    



}
