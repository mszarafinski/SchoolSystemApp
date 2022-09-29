package com.company;

import com.company.dto.GradeResponse;
import com.company.entity.Grade;
import com.company.entity.SchoolClass;
import com.company.entity.Student;
import com.company.entity.Subject;
import org.springframework.stereotype.Component;

@Component
public class GradeMapper {

    public GradeResponse mapEntityToResponse (Grade grade){
        return GradeResponse.builder()
                .date(grade.getDate())
                .value(grade.getValue())
                .id(grade.getId())
                .studentId(grade.getStudent().getId())
                .subjectId(grade.getSubject().getId())
                .build();

    }

}
