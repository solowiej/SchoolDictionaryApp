package com.restapi.rest.model.dto;

import com.restapi.rest.model.GradeSubject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGradeRequest {
    private Long id;
    private GradeSubject subject;
    private Double value;
}
