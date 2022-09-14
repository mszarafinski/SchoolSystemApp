package com.company;

import com.company.entity.SchoolClass;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class Student {

    private String firstName;
    private String secondName;
    private Map<Subject, ArrayList> gradeBook;
    private Subject [] subjects = Subject.values();
    private List<Double> averages;
    private SchoolClass aClass;

    Scanner sc = new Scanner(System.in);

    public Student(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;

        gradeBook = new TreeMap<Subject, ArrayList>();

        for (Subject subject : subjects) {
            gradeBook.put(subject, new ArrayList<Grade>());
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Map<Subject, ArrayList> getGradeBook() {
        return gradeBook;
    }

    public Subject[] getSubjects() {
        return subjects;
    }

    public SchoolClass getaClass() {
        return aClass;
    }

    public void setaClass(SchoolClass aClass) {
        this.aClass = aClass;
    }

    public String getClassName(){
        return this.aClass.getClassName();
    }


    public void showStudentGradesForTheGivenSubject(Subject subject){
        List <Grade> gradeList = this.gradeBook.get(subject);
        if(gradeList.isEmpty()){
            System.out.print("\tNo grades yet");
        }
        System.out.print("\t" + subject + ": ");
        for (Grade grade : gradeList) {
            System.out.print("\t" + grade + ", ");
        }
    }

    public Grade addGrade(Subject subject){

        Grade grade;
        LocalDate gradeDate = setGradeDate(sc);
        int gradeValue;

        do{
            System.out.print("\tEnter the grade from 1 to 6: ");
            gradeValue = sc.nextInt();
            if(gradeValue < 1 || gradeValue > 6){
                System.out.println("\tIncorrect grade value. Enter the number from 1 to 6");
            }
        }
        while(gradeValue < 1 || gradeValue > 6);

        grade = new Grade(gradeValue,gradeDate);

        this.gradeBook.get(subject).add(grade);

        return grade;

    }

    public void showAverageGrade(Subject subject){
        List<Grade> grades = this.gradeBook.get(subject);
    }

    public void printSubjects(){

        for(Subject i : gradeBook.keySet()){
            System.out.println("\t"+i.ordinal()+1 + ": " + i);
        }
    }

    public void showStudentGrades(){

        for(Map.Entry<Subject,ArrayList> entry : gradeBook.entrySet()){
            Subject subject = entry.getKey();
            ArrayList<Grade> grades = entry.getValue();

            System.out.print("\t"+subject.getName() + ":\t");

            for (int i=0; i<grades.size(); i++ ) {
                System.out.print( "\t"+ grades.get(i).getGrade());
                if( i < grades.size()-1){
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    public void showStudentGradesInTimePeriod(LocalDate startingDate, LocalDate endingDate){

        for(Map.Entry<Subject,ArrayList> entry : gradeBook.entrySet()){
            Subject subject = entry.getKey();
            ArrayList<Grade> grades = entry.getValue();

            System.out.print(subject.getName() + ":\t");

            for (int i=0; i<grades.size(); i++ ) {
                if(grades.get(i).getDate().isBefore(startingDate) || grades.get(i).getDate().isAfter(endingDate)){
                    continue;
                } else {
                    System.out.print("\t"+grades.get(i).getGrade() + " (" + grades.get(i).getDate().toString() + ")");
                }
                if( i < grades.size()-1){
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    public void showStudentAverages(){

        for(Map.Entry<Subject,ArrayList> entry : gradeBook.entrySet()){
            Subject subject = entry.getKey();
            ArrayList<Grade> grades = entry.getValue();

            System.out.print(subject.getName() + ":\t");

            if (grades.isEmpty()){
                System.out.println();
                continue;
            }

            double average = getSubjectAverage(grades);
            System.out.println(average);
        }
    }

    public double getSubjectAverage(ArrayList<Grade> grades) {
        int sum =0;
        for (int i = 0; i< grades.size(); i++ ) {
            sum += grades.get(i).getGrade();
        }
        return sum / grades.size();
    }

    public double getTotalAverage(){

        int numberOfSubjets = 1;
        double totalAverage = 0;

        for(Map.Entry<Subject,ArrayList> entry : gradeBook.entrySet()){
            ArrayList<Grade> grades = entry.getValue();

            if (grades.isEmpty()){
                continue;
            }

            double average = getSubjectAverage(grades);
            totalAverage += average;

            numberOfSubjets++;
        }
        return totalAverage/numberOfSubjets;
    }

    public static LocalDate setGradeDate(Scanner sc) {
        int year;
        Month month;
        int day = 0;

        sc.useDelimiter("/|\\n");

        while(true){
            System.out.print("<INPUT> Enter the date in yyyy/MM/dd format >> ");
            year = sc.nextInt();
            try {
                month = Month.of(sc.nextInt());
            } catch (DateTimeException dateTimeException){
                sc.nextLine();
                System.out.printf("<ERROR> Invalid month input. Month number cannot be less than 1 or greater than 12. Try again\n");
                continue;
            }
            day = sc.nextInt();
            if( day > month.maxLength() || day < 1 ){
                System.out.printf("<ERROR> Invalid day input. Day number cannot be less than 1 or greater than max length of %s (%d). Try again\n", month.toString().toLowerCase(), month.maxLength());
                continue;
            }
            break;
        }
        return LocalDate.of(year,month,day);
    }


}
