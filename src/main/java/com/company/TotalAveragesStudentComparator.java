package com.company;

import com.company.entity.Student;

import java.util.Comparator;

public class TotalAveragesStudentComparator implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {
//        return Double.compare(s1.calculateStudentTotalAverage(),s2.calculateStudentTotalAverage());
        return Double.compare(s1.getTotalAverage(),s2.getTotalAverage());
    }

}
