package com.company.entity;


import com.company.SubjectName;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "Subject")
@Table(name = "subjects")
public class Subject {

    @Id
    @SequenceGenerator(
            name = "subject_sequence",
            sequenceName = "subject_sequence",
            allocationSize = 1)
    @GeneratedValue(generator = "subject_sequence", strategy = GenerationType.SEQUENCE)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @JsonIgnore
    @OneToMany(
            mappedBy = "subject",
            fetch = FetchType.EAGER
    )
    private Set<Grade> grades = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "students_subjects",
            joinColumns = @JoinColumn(
                    name = "student_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "subject_id", referencedColumnName = "id"
            )
    )
    private Set<Student> students = new HashSet<>();


    public Subject() {
    }

    public Subject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
