package com.company.service;

import com.company.SubjectName;
import com.company.entity.Subject;
import com.company.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class SubjectService {

    private SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject findById(Long id){

        Optional<Subject> subjectOptional = subjectRepository.findById(id);

        if(subjectOptional.isPresent()) {
            return subjectOptional.get();
        }
        else {
            throw new NullPointerException("Subject does not exists");
        }
    }

    public List<Subject> findAll(){
        return subjectRepository.findAll();
    }

    public void saveSubject(Subject subject){
        subjectRepository.save(subject);
    }
}
