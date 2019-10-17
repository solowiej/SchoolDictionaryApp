package com.restapi.rest.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignStudentToStudentGroupRequest {
    private Long studentId;
    private Long studentGroupId;
}
