package com.company.service;

import com.company.entity.Grade;
import com.company.entity.Student;
import com.company.entity.Subject;
import com.company.repository.GradeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GradeService {

    private GradeRepository gradeRepository;
    private StudentService studentService;
    private SubjectService subjectService;

    public GradeService(GradeRepository gradeRepository, StudentService studentService, SubjectService subjectService) {
        this.gradeRepository = gradeRepository;
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    @Transactional
    public void addNewGrade(Long studentId, Long subjectId, Integer gradeValue, LocalDate date){

        if(gradeValue>= 1 && gradeValue<=6){

            Student student = studentService.findById(studentId);
            Subject subject = subjectService.findById(subjectId);

            Grade grade = new Grade(student, subject, gradeValue, date);

            grade.addGradeToStudentAndSubject();

            gradeRepository.save(grade);


        }else {
            throw new IllegalArgumentException("Grade must be between 1 and 6.");
        }

    }







}
