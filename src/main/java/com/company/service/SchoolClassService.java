package com.company.service;

import com.company.entity.SchoolClass;
import com.company.repository.SchoolClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolClassService {
    private SchoolClassRepository schoolClassRepository;


    @Autowired
    public SchoolClassService(SchoolClassRepository schoolClassRepository) {
        this.schoolClassRepository = schoolClassRepository;
    }

    public List<SchoolClass> getAllClasses() {
        return schoolClassRepository.findAll();
    }

    public void saveClass(SchoolClass schoolClass) {
        schoolClassRepository.save(schoolClass);
    }

    public void createNewClass(SchoolClass schoolClass){
        schoolClassRepository.save(schoolClass);
    }

    public SchoolClass findById(Long id) {
        Optional<SchoolClass> classOptional = schoolClassRepository.findById(id);

        if (classOptional.isPresent()) {
            return classOptional.get();
        } else {
            throw new NullPointerException("Class does not exist");
        }
    }

    public boolean checkIfClassExists(String className) {
        return schoolClassRepository.existsByClassName(className);
    }

    public boolean checkIfNoClasses() {
        if (schoolClassRepository.count() == 0) {
            return true;
        }
        return false;
    }

    public List<SchoolClass> showAllClassesAsObjects() {
        return schoolClassRepository.findAll();
    }

    public void showAllClassesAsNames() {
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

    public SchoolClass findByClassName(String className) {
        return schoolClassRepository.findByClassName(className);
    }


    public long getNumberOfClasses() {
        return schoolClassRepository.count();
    }

}
