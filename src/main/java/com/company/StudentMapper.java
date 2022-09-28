package com.company;

import com.company.dto.StudentRequest;
import com.company.dto.StudentResponse;
import com.company.entity.Grade;
import com.company.entity.SchoolClass;
import com.company.entity.Student;
import com.company.entity.Subject;
import com.company.repository.StudentRepository;
import com.company.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.util.Set;

@AllArgsConstructor
@Component
public class StudentMapper {


    public StudentResponse mapEntityToResponse(Student student) {

        if (!(student.getSchoolClass() == null)) {
            return StudentResponse
                    .builder()
                    .id(student.getId())
                    .firstName(student.getFirstName())
                    .lastName(student.getLastName())
                    .classId(student.getSchoolClass().getId())
                    .totalAverage(student.getTotalAverage())
                    .build();

        } else {
            return StudentResponse
                    .builder()
                    .id(student.getId())
                    .firstName(student.getFirstName())
                    .lastName(student.getLastName())
                    .totalAverage(student.getTotalAverage())
                    .build();
        }
    }

    public Student mapRequestToEntity(
            StudentRequest studentRequest,
            SchoolClass schoolClass,
            Set<Grade> grades,
            Set<Subject> subjects
    ) {
        return Student.builder()
                .firstName(studentRequest.getFirstName())
                .lastName(studentRequest.getLastName())
                .schoolClass(schoolClass)
                .grades(grades)
                .subjects(subjects)
                .build();

    }


}
