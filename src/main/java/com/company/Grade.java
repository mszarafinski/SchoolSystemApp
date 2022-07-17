package com.company;

import java.time.LocalDate;
import java.util.Scanner;

public class Grade {

    private int grade;
    private LocalDate date;
    Scanner sc = new Scanner(System.in);


    public Grade(int grade, LocalDate date) {
        this.grade = grade;
        this.date = date;
    }

    public int getGrade() {
        return grade;
    }

    public LocalDate getDate() {
        return date;
    }

}
