package com.company.service;

import com.company.entity.SchoolClass;
import com.company.entity.Student;
import com.company.entity.Subject;
import com.company.repository.StudentRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private SchoolClassService schoolClassService;
    private SubjectService subjectService;

    public StudentService(StudentRepository studentRepository, SchoolClassService schoolClassService, SubjectService subjectService) {
        this.studentRepository = studentRepository;
        this.schoolClassService = schoolClassService;
        this.subjectService = subjectService;
    }

    public boolean checkIfStudentExistsByName(String firstName, String lastName) {
        return studentRepository.existsByFirstNameAndLastName(firstName, lastName);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        addSubjectsToStudent(student);
        studentRepository.save(student);
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }


    @Transactional
    public void addNewStudent(Student student, Long classId) {
        SchoolClass schoolClass = schoolClassService.findById(classId);
        student.addStudentToClass(schoolClass);
        addSubjectsToStudent(student);
        studentRepository.save(student);
    }

    public void changeStudentClass(Long studentId, Long classId) {
        SchoolClass newSchoolClass = schoolClassService.findById(classId);
        Student student = findById(studentId);

        if (!(student.getSchoolClass() == null)) {
            if (student.getSchoolClass().getId().equals(classId)) {
                throw new IllegalArgumentException("The student is already assigned to the " + newSchoolClass.getClassName() + " class");
            }
        }
        student.setSchoolClass(newSchoolClass);

        studentRepository.save(student);
    }

    public Student findById(Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (studentOptional.isPresent()) {
            return studentOptional.get();
        } else {
            throw new NullPointerException("Student does not exist");
        }
    }

    public void deleteStudentById(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public void addSubjectsToStudent(Student student) {
        List<Subject> subjects = subjectService.findAll();

        for (Subject subject : subjects) {
            student.addSubjectToStudent(subject);
        }
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


    @Modifying
    public void deleteStudentById(Student student) {
        studentRepository.delete(student);
    }


    public Student findbyLastName(String lastName) {
        return studentRepository.findByLastName(lastName);
    }


//    public void changeStudentClass(Student student, SchoolClass newClass){
//        student.setSchoolClass(newClass);
//    }

}
