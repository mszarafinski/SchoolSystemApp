package com.company;

import com.company.entity.Grade;
import com.company.entity.SchoolClass;
import com.company.entity.Student;
import com.company.entity.Subject;
import com.company.service.SchoolClassService;
import com.company.service.StudentService;
import com.company.service.SubjectService;
import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Component
public class DataSupplier {

    private SubjectService subjectService;
    private SchoolClassService schoolClassService;
    private StudentService studentService;

    public DataSupplier(SubjectService subjectService, SchoolClassService schoolClassService, StudentService studentService) {
        this.subjectService = subjectService;
        this.schoolClassService = schoolClassService;
        this.studentService = studentService;

    }

//    @Bean
//    public void createSubjectsList() {
//        Arrays.stream(SubjectName.values()).forEach(
//                subjectName -> {
//                    String name = subjectName.getName();
//                    Subject subject = new Subject(name);
//                    subjectRepository.save(subject);
//                }
//        );
//    }


    public Set<Subject> getSubjectsSet() {
        SubjectName[] subjectNames = SubjectName.values();
        Set<Subject> subjects = new HashSet<>();

        for (SubjectName subjectName : subjectNames) {
            subjects.add(new Subject(subjectName.getName()));
        }

        return subjects;
    }


    @Bean
    @Transactional
    public void populateBasicData() {

        Set<Subject> subjects = getSubjectsSet();

        SchoolClass schoolClass1a = new SchoolClass("1A");
        SchoolClass schoolClass1b = new SchoolClass("1B");
        SchoolClass schoolClass1c = new SchoolClass("1C");

        List<Student> students = new ArrayList<>();

        Random rnd = new Random();
        Faker faker = new Faker();

        int numberOfStudentsToBeAdded = 6;

        for (int i = 1; i <= numberOfStudentsToBeAdded; i++) {

            Student student;

            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();

            student = new Student(firstName, lastName);

            if (i <= numberOfStudentsToBeAdded / 3) {
                student.addStudentToClass(schoolClass1a);
            }
            else if ( i <= (2* numberOfStudentsToBeAdded) / 3) {
                student.addStudentToClass(schoolClass1b);
            } else {
                student.addStudentToClass(schoolClass1c);
            }

            students.add(student);
        }

        for (Student student : students) {
            for (Subject subject : subjects) {
                student.addSubjectToStudent(subject);
            }
        }

        for (Student student : students) {

            Set<Subject> studentSubjects = student.getSubjects();

            for (Subject subject : studentSubjects) {

                int numberOfGradesToBeAdded = 2;

                for (int i = 0; i < numberOfGradesToBeAdded; i++) {
                    int gradeValue = rnd.nextInt(1, 7);

                    Date fakerDate = faker.date().between(
                            new Date(122, 1, 1),
                            new Date(122, 12, 30)
                    );
                    LocalDate date = fakerDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    Grade grade = new Grade(student, subject, gradeValue, date);
                    grade.addGradeToStudentAndSubject();

                }
            }
        }

        schoolClassService.saveClass(schoolClass1a);
        schoolClassService.saveClass(schoolClass1b);
        schoolClassService.saveClass(schoolClass1c);
    }




//
//    public void createSampleStudentsAndClasses() {
//
//        SchoolClass schoolClass1a = new SchoolClass("1A");
//        SchoolClass schoolClass1b = new SchoolClass("1B");
//
//        schoolClassService.createNewClass(schoolClass1a);
//        schoolClassService.createNewClass(schoolClass1b);
//
//        int numberOfStudentsToBeAdded = 3;
//
//        for (int i = 1; i <= numberOfStudentsToBeAdded; i++) {
//
//            Faker faker = new Faker();
//            Student student;
//
//            String firstName = faker.name().firstName();
//            String lastName = faker.name().lastName();
//
//
//            if (i <= numberOfStudentsToBeAdded/2) {
//                student = new Student(firstName, lastName, schoolClass1a);
//                studentService.addNewStudent(student, schoolClass1a.getId());
//
//            } else {
//                student = new Student(firstName, lastName, schoolClass1b);
//                studentService.addNewStudent(student, schoolClass1b.getId());
//            }
//        }
//
//    }
//
//
//    public void addRandomGradesToStudents(){
//        List<Student> students = studentService.getAllStudents();
//        Random rnd = new Random();
//        Faker faker = new Faker();
//
//        for (Student student : students) {
//
//            Set<Subject> subjects = student.getSubjects();
//
//            for (Subject subject : subjects) {
//
//                int numberOfGradesToBeAdded = 4;
//
//                for (int i = 0; i < numberOfGradesToBeAdded; i++) {
//                    Long studentId = student.getId();
//                    Long subjectId = subject.getId();
//                    int gradeValue = rnd.nextInt(1,7);
//
//                    Date fakerDate = faker.date().between(
//                            new Date(122, 1, 1),
//                            new Date(122, 12, 30)
//                    );
//                    LocalDate date = fakerDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//
//                    gradeService.addNewGrade(studentId, subjectId,gradeValue,date);
//                }
//            }
//
//        }
//
//    }
//
//
//

}
