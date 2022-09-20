package com.company.entity;

import javax.persistence.*;

import java.util.List;

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

//    @OneToMany(
//            mappedBy = "student",
//            fetch = FetchType.EAGER,
//            orphanRemoval = true,
//            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
//    private List<Grade> grades;

    public Student() {
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

//    public List<Grade> getGrades() {
//        return grades;
//    }
//
//    public void setGrades(List<Grade> grades) {
//        this.grades = grades;
//    }

    //    public void showStudentGradesForTheGivenSubject(Subject subject){
//        List <Grade> gradeList = this.gradeBook.get(subject);
//        if(gradeList.isEmpty()){
//            System.out.print("\tNo grades yet");
//        }
//        System.out.print("\t" + subject + ": ");
//        for (Grade grade : gradeList) {
//            System.out.print("\t" + grade + ", ");
//        }
//    }

//    public Grade addGrade(Subject subject) {
//
//        Grade grade;
//        LocalDate gradeDate = setGradeDate(sc);
//        int gradeValue;
//
//        do {
//            System.out.print("\tEnter the grade from 1 to 6: ");
//            gradeValue = sc.nextInt();
//            if (gradeValue < 1 || gradeValue > 6) {
//                System.out.println("\tIncorrect grade value. Enter the number from 1 to 6");
//            }
//        }
//        while (gradeValue < 1 || gradeValue > 6);
//
//        grade = new Grade(gradeValue, gradeDate);
//
////        this.gradeBook.get(subject).add(grade);
//
//        return grade;
//
//    }

////    public void showAverageGrade(Subject subject){
//        List<Grade> grades = this.gradeBook.get(subject);
//    }

//    public void printSubjects(){
//
//        for(Subject i : gradeBook.keySet()){
//            System.out.println("\t"+i.ordinal()+1 + ": " + i);
//        }
//    }

//    public void showStudentGrades(){
//
//        for(Map.Entry<Subject,ArrayList> entry : gradeBook.entrySet()){
//            Subject subject = entry.getKey();
//            ArrayList<Grade> grades = entry.getValue();
//
//            System.out.print("\t"+subject.getName() + ":\t");
//
//            for (int i=0; i<grades.size(); i++ ) {
//                System.out.print( "\t"+ grades.get(i).getGrade());
//                if( i < grades.size()-1){
//                    System.out.print(", ");
//                }
//            }
//            System.out.println();
//        }
//    }

//    public void showStudentGradesInTimePeriod(LocalDate startingDate, LocalDate endingDate){
//
//        for(Map.Entry<Subject,ArrayList> entry : gradeBook.entrySet()){
//            Subject subject = entry.getKey();
//            ArrayList<Grade> grades = entry.getValue();
//
//            System.out.print(subject.getName() + ":\t");
//
//            for (int i=0; i<grades.size(); i++ ) {
//                if(grades.get(i).getDate().isBefore(startingDate) || grades.get(i).getDate().isAfter(endingDate)){
//                    continue;
//                } else {
//                    System.out.print("\t"+grades.get(i).getGrade() + " (" + grades.get(i).getDate().toString() + ")");
//                }
//                if( i < grades.size()-1){
//                    System.out.print(", ");
//                }
//            }
//            System.out.println();
//        }
//    }

////    public void showStudentAverages(){
////
////        for(Map.Entry<Subject,ArrayList> entry : gradeBook.entrySet()){
////            Subject subject = entry.getKey();
////            ArrayList<Grade> grades = entry.getValue();
////
////            System.out.print(subject.getName() + ":\t");
////
////            if (grades.isEmpty()){
////                System.out.println();
////                continue;
////            }
////
////            double average = getSubjectAverage(grades);
////            System.out.println(average);
////        }
////    }
//
//    public double getSubjectAverage(ArrayList<Grade> grades) {
//        int sum = 0;
//        for (int i = 0; i < grades.size(); i++) {
//            sum += grades.get(i).getGrade();
//        }
//        return sum / grades.size();
//    }
//
////    public double getTotalAverage(){
////
////        int numberOfSubjets = 1;
////        double totalAverage = 0;
////
////        for(Map.Entry<Subject,ArrayList> entry : gradeBook.entrySet()){
////            ArrayList<Grade> grades = entry.getValue();
////
////            if (grades.isEmpty()){
////                continue;
////            }
////
////            double average = getSubjectAverage(grades);
////            totalAverage += average;
////
////            numberOfSubjets++;
////        }
////        return totalAverage/numberOfSubjets;
////    }
//
//    public static LocalDate setGradeDate(Scanner sc) {
//        int year;
//        Month month;
//        int day = 0;
//
//        sc.useDelimiter("/|\\n");
//
//        while (true) {
//            System.out.print("<INPUT> Enter the date in yyyy/MM/dd format >> ");
//            year = sc.nextInt();
//            try {
//                month = Month.of(sc.nextInt());
//            } catch (DateTimeException dateTimeException) {
//                sc.nextLine();
//                System.out.printf("<ERROR> Invalid month input. Month number cannot be less than 1 or greater than 12. Try again\n");
//                continue;
//            }
//            day = sc.nextInt();
//            if (day > month.maxLength() || day < 1) {
//                System.out.printf("<ERROR> Invalid day input. Day number cannot be less than 1 or greater than max length of %s (%d). Try again\n", month.toString().toLowerCase(), month.maxLength());
//                continue;
//            }
//            break;
//        }
//        return LocalDate.of(year, month, day);
//    }


}
