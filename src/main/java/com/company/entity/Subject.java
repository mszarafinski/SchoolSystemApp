//package com.company.entity;
//
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity(name = "Subject")
//@Table(name = "subject")
//public class Subject {
//
//    @Id
//    @SequenceGenerator(
//            name = "subject_sequence",
//            sequenceName = "subject_sequence",
//            allocationSize = 1)
//    @GeneratedValue(generator = "subject_sequence", strategy = GenerationType.SEQUENCE)
//    @Column(
//            name = "id",
//            updatable = false
//    )
//    private Long id;
//    @Column(
//            name = "name",
//            nullable = false,
//            columnDefinition = "TEXT"
//    )
//    private String name;
//
//    @OneToMany(
//            mappedBy = "subject",
//            fetch = FetchType.EAGER,
//            cascade = CascadeType.REMOVE,
//            orphanRemoval = true
//    )
//    private List<Grade> grades;
//
//    public Subject() {
//    }
//
//    public Subject(String name) {
//        this.name = name;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<Grade> getGrades() {
//        return grades;
//    }
//
//    public void setGrades(List<Grade> grades) {
//        this.grades = grades;
//    }
//}
