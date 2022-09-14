package com.company;

import com.company.entity.SchoolClass;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class School {

    private String name;
    private List<SchoolClass> classes;
    private ArrayList<Student> students;
    Scanner sc = new Scanner(System.in);

    public School(String name) {
        this.name = name;
        classes = new ArrayList<>();
        students = new ArrayList<>();
    }

    public List<SchoolClass> getClasses() {
        return classes;
    }

    public String getName() {
        return this.name;
    }


    public Student createNewStudent(String firstName, String secondName){
        Student student = new Student(firstName,secondName);
        this.students.add(student);
        return student;
    }

    public ArrayList<Student> getSortedStudentsList() {
        Comparator<Student> compareBySecondNameAndClassName = Comparator
                .comparing(Student::getClassName)
                .thenComparing(Student::getSecondName);

        ArrayList<Student> sortedStudentList = this.students.stream()
                .sorted(compareBySecondNameAndClassName)
                .collect(Collectors.toCollection(ArrayList::new));

        return sortedStudentList;
    }

    public void showAllTheClassesAndStudentsNumber(){

        if(classes.isEmpty()){
            System.out.println("\tThere are no classes yet.");
        } else {
            for (SchoolClass aClass : classes) {
                System.out.print("\t\t"+aClass.getClassName() + ": \t");
//                System.out.println(aClass.getStudentsList().size());
            }
        }
    }

    public boolean checkIfClassExists(String className){
        for (SchoolClass aClass : classes) {
            if(aClass.getClassName().equals(className)){
                return true;
            }
        }
        return false;
    }

    public boolean checkIfStudentExists(String firstName, String secondName){
        for (Student student : students) {
            if(student.getFirstName().equals(firstName) && student.getSecondName().equals(secondName) ){
                return true;
            }
        }
        return false;
    }

    public void assignStudentToClass(Student student, String className ){

        for (SchoolClass aClass : classes) {
            if(aClass.getClassName().equals(className)){
//                aClass.getStudentsList().add(student);
                student.setaClass(aClass);
            }
        }
    }

    public void showAllTheStudents(){

        ArrayList<Student> sortedStudentsList = getSortedStudentsList();

        for (int i=0; i<sortedStudentsList.size(); i++) {
            System.out.printf("\t\t%d:\t%s %s (%s)\n", i+1,sortedStudentsList.get(i).getFirstName(), sortedStudentsList.get(i).getSecondName(), sortedStudentsList.get(i).getaClass().getClassName());
        }
    }

    public ArrayList<Student> sortStudentsByTotalAverage(){

        return this.students
                .stream()
                .sorted(Comparator.comparing(Student::getTotalAverage).reversed())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void moveStudentToAnotherClass(Student student, String className){

        SchoolClass aclass = student.getaClass();

        for (SchoolClass aClass : this.classes) {

            if(aClass.getClassName().equals(className)){
                aclass = aClass;
//                student.getaClass().getStudentsList().remove(student);
                student.setaClass(aclass);
                break;
            }
        }
    }

    public void removeStudent(Student student){

        for (Student aStudent : this.getSortedStudentsList()) {
            if(aStudent.equals(student)){
                this.students.remove(aStudent);
//                aStudent.getaClass().getStudentsList().remove(aStudent);
            }
        }
    }

}
