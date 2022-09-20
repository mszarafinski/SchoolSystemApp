package com.company.controller;

import com.company.entity.Student;
import com.company.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping(path = "/all")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping
    public void addNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }



}
