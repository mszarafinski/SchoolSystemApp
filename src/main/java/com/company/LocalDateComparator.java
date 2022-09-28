package com.company;

import com.company.entity.Grade;

import java.time.LocalDate;
import java.util.Comparator;

public class LocalDateComparator implements Comparator<Grade> {



    @Override
    public int compare(Grade g1, Grade g2) {

        LocalDate date1 = g1.getDate();
        LocalDate date2 = g2.getDate();

        if(date1.isEqual(date2)){
            return -0;
        }
        else if(date1.isAfter(date2)){
            return 1;
        }
        else {
            return -1;
        }

    }

}
