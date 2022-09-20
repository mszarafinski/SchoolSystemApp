package com.company.service;

import com.company.entity.SchoolClass;
import com.company.entity.Student;
import com.company.repository.SchoolClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class SchoolClassService {

    private SchoolClassRepository schoolClassRepository;
    private Scanner sc;

    @Autowired
    public SchoolClassService(SchoolClassRepository schoolClassRepository) {
        this.schoolClassRepository = schoolClassRepository;
    }

    public void createNewClass(SchoolClass schoolClass){
        this.schoolClassRepository.save(schoolClass);
    }


    public boolean checkIfClassExists(String className){
        return schoolClassRepository.existsByClassName(className);
    }

    public boolean checkIfNoClasses(){
        if(schoolClassRepository.count() == 0){
            return true;
        }
        return false;
    }

    public List<SchoolClass> showAllClassesAsObjects(){
        return schoolClassRepository.findAll();
    }

    public void showAllClassesAsNames(){
        showAllClassesAsObjects().stream().forEach(schoolClass -> System.out.println(schoolClass.getClassName()));
    }

//    @Transactional
//    public void addNewStudentToClass(Student student, SchoolClass schoolClass){
//        if(!schoolClass.getStudents().contains(student)){
//            schoolClass.getStudents().add(student);
//            student.setSchoolClass(schoolClass);
//        }
//    }

//    @Transactional
    public SchoolClass findByClassName (String className){
        return schoolClassRepository.findByClassName(className);
    }


    public long getNumberOfClasses(){
       return  schoolClassRepository.count();
    }



}
