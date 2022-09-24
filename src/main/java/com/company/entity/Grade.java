package com.company.entity;

import com.company.SubjectName;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

@Entity(name = "Grade")
@Table(name = "grades")
public class Grade {

    @Id
    @SequenceGenerator(
            name = "grade_sequence",
            sequenceName = "grade_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            generator = "grade_sequence",
            strategy = GenerationType.SEQUENCE
    )

    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "value",
            nullable = false,
            length = 1
    )
    private Integer value;

    @Column(
            name = "date",
            nullable = false
//            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "subject_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "grade_subject_fk")
    )
    private Subject subject;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "grade_student_fk")
    )
    private Student student;

    public Grade() {
    }

    public Grade(Integer value, LocalDate date, Subject subject) {
        this.value = value;
        this.date = date;
        this.subject = subject;
    }

    public Grade(Student student, Subject subject, Integer value, LocalDate date) {
        this.value = value;
        this.date = date;
        this.subject = subject;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

        public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void addGradeToStudentAndSubject(){

        student.getGrades().add(this);
        subject.getGrades().add(this);

    }
}
