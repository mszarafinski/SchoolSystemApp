package com.company.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.GenerationType.SEQUENCE;


@Entity(name = "Class")
@Table(
        name = "classes",
        uniqueConstraints = @UniqueConstraint(name = "class_name_unique", columnNames = "class_name")
)
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

//    //    @JsonIgnore
//    @OneToMany(mappedBy = "schoolClass", fetch = FetchType.EAGER)
//    private List<Student> students = new ArrayList<>();

    public SchoolClass() {
    }

    public SchoolClass(String className) {
        this.className = className.toUpperCase(Locale.ENGLISH);
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
        this.className = className.toUpperCase();
    }

//    public List<Student> getStudents() {
//        return students;
//    }
//
//    public void setStudents(List<Student> students) {
//        this.students = students;
//    }

//    public void addStudent(Student student) {
//        if (!this.students.contains(student)) {
//            this.students.add(student);
//            student.setSchoolClass(this);
//        }
//    }
//
//    public void deleteStudent(Student student) {
//        if (this.students.contains(student)) {
//            student.setSchoolClass(null);
//            this.students.remove(student);
//        }
//    }

    @Override
    public String toString() {
        return "SchoolClass{" +
                "id=" + id +
                ", className='" + className + '\'' +
                '}';
    }

}
