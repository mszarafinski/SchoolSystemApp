package com.company.service;

import com.company.entity.SchoolClass;
import com.company.entity.Student;
import com.company.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void createNewStudent(Student student){
        studentRepository.save(student);
    }

    public List<Student> getSortedStudentsList() {
        Sort sort = Sort.by("lastName").ascending();

        return studentRepository
                .findAll(sort);


    }

    public void printStudentList(List<Student> students ){

        students.
                forEach(
                        student -> {
                            String firstName = student.getFirstName();
                            String lastName = student.getLastName();
                            String className = student.getClass().getName();
                            Long id = student.getId();
                            System.out.println(
                                    firstName + " " + lastName + " (class: " + className + ", id: " + id + " )"
                                    );
                        }
                );

    }

    public Optional<Student> findStudentById(Long studentId){
        return studentRepository.findById(studentId);

    }

    public void deleteStudent(Student student){
        studentRepository.delete(student);
    }
}
