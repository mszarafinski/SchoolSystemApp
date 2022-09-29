package com.company;

import com.company.dto.StudentRequest;
import com.company.dto.StudentResponse;
import com.company.entity.Grade;
import com.company.entity.SchoolClass;
import com.company.entity.Student;
import com.company.entity.Subject;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class StudentMapper {


    public StudentResponse mapEntityToResponse(Student student) {

        return StudentResponse
                .builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .classId(student.getSchoolClass().getId())
                .totalAverage(student.getTotalAverage())
                .build();


    }

    public Student mapRequestToEntity(
            StudentRequest studentRequest

    ) {
        return Student.builder()
                .firstName(studentRequest.getFirstName())
                .lastName(studentRequest.getLastName())
                .grades(new HashSet<Grade>())
                .subjects(new HashSet<Subject>())
                .build();

    }


}
