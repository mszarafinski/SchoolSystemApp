package com.company.entity;

import com.company.SubjectName;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

@Entity(name = "Grade")
@Table(name = "grade")
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
    private int value;


    @Column(
            name = "date",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDate date;

    @ManyToOne
    @JoinColumn(
            name = "subject_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "grade_subject_fk")
    )
    private Subject subject;

    @ManyToOne
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "grade_student_fk")
    )
    private Student student;

    public Grade() {
    }

    public Grade(int value, LocalDate date, Subject subject) {
        this.value = value;
        this.date = date;
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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
}
