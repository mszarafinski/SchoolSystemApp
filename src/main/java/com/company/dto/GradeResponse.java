package com.company.dto;

import com.company.entity.Student;
import com.company.entity.Subject;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GradeResponse {

    private Long id;
    private LocalDate date;
    private Integer value;
    private Long studentId;
    private Long subjectId;

}
