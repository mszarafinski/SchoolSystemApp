package com.company.entity;

import com.company.Student;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static javax.persistence.GenerationType.SEQUENCE;


@Entity(name = "Class")
@Table(name = "class",
        uniqueConstraints = @UniqueConstraint(name = "claas_name_unique", columnNames = "class_name"))

public class SchoolClass {

    @Id
    @SequenceGenerator(
            name = "class_sequence",
            sequenceName = "class_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "class_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "class_name",
            nullable = false,
            updatable = false,
            columnDefinition = "TEXT"
    )
    private String className;

    //    private List<Student> studentsList;
//    Scanner sc = new Scanner(System.in);

    public SchoolClass() {
    }

    public SchoolClass(String className) {
        this.className = className.toUpperCase(Locale.ENGLISH);
//        this.studentsList = new ArrayList<Student>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "SchoolClass{" +
                "id=" + id +
                ", className='" + className + '\'' +
                '}';
    }

    //    public List<Student> getStudentsList() {
//        return studentsList;
//    }

//    public void addStudentToClass(Student student){
//        this.studentsList.add(student);
//    }

}
