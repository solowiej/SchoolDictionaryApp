package com.restapi.rest.model.dto;

import com.restapi.rest.model.GradeSubject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddGradeToStudentRequest {
    private Long studentId;

    private Double value;
    private GradeSubject gradeSubject;
}
