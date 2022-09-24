package com.company;

import com.company.entity.Grade;
import com.company.entity.SchoolClass;
import com.company.entity.Student;
import com.company.entity.Subject;
import com.company.repository.SubjectRepository;
import com.company.service.GradeService;
import com.company.service.SchoolClassService;
import com.company.service.StudentService;
import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Component
public class DataSupplier {

    private SubjectRepository subjectRepository;
    private SchoolClassService schoolClassService;
    private StudentService studentService;
    private GradeService gradeService;

    public DataSupplier(SubjectRepository subjectRepository, SchoolClassService schoolClassService, StudentService studentService, GradeService gradeService) {
        this.subjectRepository = subjectRepository;
        this.schoolClassService = schoolClassService;
        this.studentService = studentService;
        this.gradeService = gradeService;
    }

    @Bean
    public void createSubjectsList() {
        Arrays.stream(SubjectName.values()).forEach(
                subjectName -> {
                    String name = subjectName.getName();
                    Subject subject = new Subject(name);
                    subjectRepository.save(subject);
                }
        );
    }

    @Bean
    public void populateBasicData(){
        createSampleStudentsAndClasses();
        addRandomGradesToStudents();
    }


    public void createSampleStudentsAndClasses() {

        SchoolClass schoolClass1a = new SchoolClass("1A");
        SchoolClass schoolClass1b = new SchoolClass("1B");

        schoolClassService.createNewClass(schoolClass1a);
        schoolClassService.createNewClass(schoolClass1b);

        int numberOfStudentsToBeAdded = 6;

        for (int i = 1; i <= numberOfStudentsToBeAdded; i++) {

            Faker faker = new Faker();
            Student student;

            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();


            if (i <= numberOfStudentsToBeAdded/2) {
                student = new Student(firstName, lastName, schoolClass1a);
                studentService.addNewStudent(student, schoolClass1a.getId());

            } else {
                student = new Student(firstName, lastName, schoolClass1b);
                studentService.addNewStudent(student, schoolClass1b.getId());
            }
        }

    }


    public void addRandomGradesToStudents(){
        List<Student> students = studentService.getAllStudents();
        Random rnd = new Random();
        Faker faker = new Faker();

        for (Student student : students) {

            Set<Subject> subjects = student.getSubjects();

            for (Subject subject : subjects) {

                int numberOfGradesToBeAdded = 4;

                for (int i = 0; i < numberOfGradesToBeAdded; i++) {
                    Long studentId = student.getId();
                    Long subjectId = subject.getId();
                    int gradeValue = rnd.nextInt(1,7);

                    Date fakerDate = faker.date().between(
                                        new Date(122, 1, 1),
                                        new Date(122, 12, 30)
                                    );
                    LocalDate date = fakerDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    gradeService.addNewGrade(studentId, subjectId,gradeValue,date);

                }
            }

        }

    }




}
