package com.company.service;

import com.company.entity.Student;
import com.company.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
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

    public boolean checkIfStudentExistsByName(String firstName, String lastName) {
        return studentRepository.existsByFirstNameAndLastName(firstName, lastName);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        studentRepository.save(student);
    }

    public boolean checkIfStudentExistsById(Long id) {
        return studentRepository.existsById(id);
    }

    public void updateStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> getSortedStudentsList() {
        Sort sort = Sort.by("lastName").ascending();

        return studentRepository
                .findAll(sort);
    }

    public void showStudentList(List<Student> students) {

        students.
                forEach(
                        student -> {
                            String firstName = student.getFirstName();
                            String lastName = student.getLastName();
//                            String className = student.getSchoolClass().getClassName();
                            Long id = student.getId();
                            System.out.println(
                                    firstName + " " + lastName + " (class: " +
//                                            className +
                                            ", id: " + id + " )"
                            );
                        }
                );

    }

    public Optional<Student> findStudentById(Long studentId) {
        return studentRepository.findById(studentId);

    }

    @Modifying
    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

//    public void changeStudentClass(Student student, SchoolClass newClass){
//        student.setSchoolClass(newClass);
//    }

}
