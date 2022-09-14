package com.company.service;

import com.company.entity.SchoolClass;
import com.company.repository.SchoolClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return this.schoolClassRepository.existsByClassName(className);
    }

    public boolean checkIfNoClasses(){
        if(schoolClassRepository.count() == 0){
            return true;
        }
        return false;
    }






}
