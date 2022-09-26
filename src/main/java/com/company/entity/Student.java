package com.company.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Student")
@Table(name = "students")
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "student_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;
    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;

    @ManyToOne
    @JoinColumn(
            name = "class_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "student_class_name_fk"),
            nullable = true
    )
    private SchoolClass schoolClass;

    @Column(
            name = "total_average",
            nullable = true,
            scale = 2,
            precision = 2,
            columnDefinition = "Decimal(3,2)"
    )
    private Double totalAverage;

    @JsonIgnore
    @OneToMany(
            mappedBy = "student",
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}
    )
    private Set<Grade> grades = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "students", cascade = {CascadeType.PERSIST, CascadeType.MERGE })
//    @JoinTable(
//            name = "students_subjects",
//            joinColumns = @JoinColumn(
//                    name = "student_id", referencedColumnName = "id"
//            ),
//            inverseJoinColumns = @JoinColumn(
//                    name = "subject_id", referencedColumnName = "id"
//            )
//    )
    private Set<Subject> subjects = new HashSet<>();

    public Student() {
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student(String firstName, String lastName, SchoolClass schoolClass) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.schoolClass = schoolClass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Double getTotalAverage() {
        return totalAverage;
    }

    public void addStudentToClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
        schoolClass.getStudents().add(this);
    }

    public void removeStudentFromClass() {
        schoolClass.getStudents().remove(this);
        this.schoolClass = null;
    }

    public void addSubjectToStudent(Subject subject) {
        this.subjects.add(subject);
        subject.getStudents().add(this);
    }

    public void removeStudentFromSubjects(){
        for (Subject subject : this.subjects) {
            subject.getStudents().remove(this);
        }
        this.getSubjects().clear();
    }


    public Double calculateSubjectAverage(Subject subject) {

        if(noGradesForSubject(subject)){
            return 0d;
        }

        List<Integer> grades = this.grades.stream()
                .filter(grade -> grade.getSubject().equals(subject))
                .map(grade -> grade.getValue())
                .collect(Collectors.toList());

        double average = grades.stream()
                .mapToDouble(Integer::intValue)
                .average()
                .getAsDouble();

        return average;
    }

    private boolean noGradesForSubject(Subject subject) {

        Set<Grade> subjectGrades = new HashSet<>();

        for (Grade grade : this.grades) {
            if(grade.getSubject().equals(subject)){
                subjectGrades.add(grade);
            }
        }

        if(subjectGrades.isEmpty()){
            return true;
        }else return false;

    }



    public Map<String,Double> calculateAveragesForAllSubjects(){

        Map<String, Double> averages = new HashMap<>();

        for (Subject subject : this.subjects) {

            String subjectName = subject.getName();
            Double average = calculateSubjectAverage(subject);

            averages.put(subjectName,average);
        }
        return averages;
    }

    @PostLoad
    public void calculateStudentTotalAverage (){

        Map<String, Double> averages = calculateAveragesForAllSubjects();

        Double sum= 0d;
        Set<String> subjectNames = averages.keySet();

        for (String subjectName : subjectNames) {

            Double average = averages.get(subjectName);
            sum += average;
        }

        this.totalAverage = new BigDecimal(sum/ averages.size()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() ;
    }



}
