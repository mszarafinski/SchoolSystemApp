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






}
