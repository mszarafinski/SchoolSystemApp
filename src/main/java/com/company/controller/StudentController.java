package com.company.controller;

import com.company.entity.Grade;
import com.company.entity.Student;
import com.company.service.GradeService;
import com.company.service.StudentService;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private StudentService studentService;
    private GradeService gradeService;

    public StudentController(StudentService studentService, GradeService gradeService) {
        this.studentService = studentService;
        this.gradeService = gradeService;
    }

    @GetMapping(path = "/all")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping(path = "/add")
    public void addNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @PostMapping(path = "/add", params = "classId")
    public void addNewStudent(@RequestBody Student student, @RequestParam(name = "classId") Long id){
         studentService.addNewStudent(student,id);
    }

    @PutMapping(path = "/update/{studentId}", params = "classId")
    public void changeStudentClass(
            @PathVariable(name = "studentId") Long studentId,
            @RequestParam(name = "classId") Long classId
    ){
        studentService.changeStudentClass(studentId,classId);
    }

    @DeleteMapping(path = "/delete/{studentId}")
    public void deleteStudentById( @PathVariable(name = "studentId") Long id){
        studentService.deleteStudentById(id);

    }

    @GetMapping(path = "/averages")
    public Map<String,Double> getStudentAveragesForAllSubjects(@RequestParam(value = "studentId") Long studentId){
       return studentService.getStudentAveragesForAllSubjects(studentId);
    }

    @GetMapping(path = "/averages/all")
    public Map<Long,Double> getAllStudentsTotalAverages(){
        return studentService.getAllStudentsTotalAverages();
    }










}
