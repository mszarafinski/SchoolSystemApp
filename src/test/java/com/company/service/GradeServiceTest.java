package com.company.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GradeServiceTest {

    private GradeService gradeService;

    public GradeServiceTest(GradeService gradeService) {
        this.gradeService = gradeService;
    }


}