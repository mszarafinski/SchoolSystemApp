package com.company.controller;

import com.company.dto.GradeResponse;
import com.company.dto.StudentRequest;
import com.company.dto.StudentResponse;
import com.company.entity.Grade;
import com.company.entity.Student;
import com.company.service.StudentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity <List<StudentResponse>> getAllStudents(){
         return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<StudentResponse> addNewStudent(@RequestBody StudentRequest studentRequest){
         return new ResponseEntity<>(studentService.addNewStudent(studentRequest), HttpStatus.CREATED) ;
    }

    @PutMapping(path = "/update/{studentId}", params = "classId")
    public ResponseEntity<StudentResponse>  changeStudentClass(
            @PathVariable(name = "studentId") Long studentId,
            @RequestParam(name = "classId") Long classId
    ){
        return new ResponseEntity<>(studentService.changeStudentClass(studentId,classId), HttpStatus.OK) ;
    }

    @DeleteMapping(path = "/delete/{studentId}")
    public ResponseEntity<StudentResponse> deleteStudentById( @PathVariable(name = "studentId") Long id){
        return new ResponseEntity<>(studentService.deleteStudentById(id), HttpStatus.OK) ;

    }

    @GetMapping(path = "grades/averages")
    public ResponseEntity<Map<String,Double>>  getStudentAveragesForAllSubjects(@RequestParam(value = "studentId") Long studentId){
       return new ResponseEntity<> (studentService.getStudentAveragesForAllSubjects(studentId), HttpStatus.OK);
    }

    @GetMapping(path = "grades/averages/all")
    public ResponseEntity<Map<Long,Double> > getAllStudentsTotalAverages(){
        return new ResponseEntity<>(studentService.getAllStudentsTotalAverages(), HttpStatus.OK) ;
    }

    @PostMapping(path = "grades/add")
    public ResponseEntity<GradeResponse>  addNewGrade(
            @RequestParam(value = "studentId") Long studentId,
            @RequestParam(value = "subjectId") Long subjectId,
            @RequestParam(value = "grade") Integer gradeValue,
            @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
       return  new ResponseEntity<>(studentService.addNewGradeToStudent(studentId,subjectId,gradeValue, date),HttpStatus.CREATED ) ;
    }

    @GetMapping(path="grades/between")
    public ResponseEntity<List<GradeResponse>>  getStudentGradesBetweenDates(
            @RequestParam(value="studentId") Long studentId,
            @RequestParam(value = "subjectId")  Long subjectId,
            @RequestParam (value = "fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam (value = "toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate
    ){
        return new ResponseEntity<>(studentService.getStudentGradesBetweenDates(studentId, subjectId, fromDate,toDate), HttpStatus.OK) ;
    }










}
