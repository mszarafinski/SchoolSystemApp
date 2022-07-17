package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class SchoolClass {

    private List<Student> studentsList;
    private String className;
    Scanner sc = new Scanner(System.in);

    public SchoolClass(String className) {
        this.className = className.toUpperCase(Locale.ENGLISH);
        this.studentsList = new ArrayList<Student>();
    }

    public List<Student> getStudentsList() {
        return studentsList;
    }

    public String getClassName() {
        return className;
    }

    public void addStudentToClass(Student student){
        this.studentsList.add(student);
    }


}
