package com.company.controller;

import com.company.dto.StudentRequest;
import com.company.dto.StudentResponse;
import com.company.entity.Grade;
import com.company.entity.Student;
import com.company.service.StudentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/all")
    public List<StudentResponse> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping(path = "/add")
    public StudentResponse addNewStudent(@RequestBody StudentRequest student){
        return studentService.addNewStudent(student);
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
    public StudentResponse deleteStudentById( @PathVariable(name = "studentId") Long id){
        return studentService.deleteStudentById(id);

    }

    @GetMapping(path = "grades/averages")
    public Map<String,Double> getStudentAveragesForAllSubjects(@RequestParam(value = "studentId") Long studentId){
       return studentService.getStudentAveragesForAllSubjects(studentId);
    }

    @GetMapping(path = "grades/averages/all")
    public Map<Long,Double> getAllStudentsTotalAverages(){
        return studentService.getAllStudentsTotalAverages();
    }

    @PostMapping(path = "grades/add")
    public void addNewGrade(
            @RequestParam(value = "studentId") Long studentId,
            @RequestParam(value = "subjectId") Long subjectId,
            @RequestParam(value = "grade") Integer gradeValue,
            @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        studentService.addNewGradeToStudent(studentId,subjectId,gradeValue, date);
    }

    @GetMapping(path="grades/between/{studentId}")
    public Set<Grade> getStudentGradesBetweenDates(
            @PathVariable(name="studentId") Long studentId,
            @RequestParam(value = "subjectId")  Long subjectId,
            @RequestParam (value = "fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam (value = "toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate
    ){
        return studentService.getStudentGradesBetweenDates(studentId, subjectId, fromDate,toDate);
    }










}
