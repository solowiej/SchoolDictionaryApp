package com.restapi.rest.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.restapi.rest.model.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentGroupRequest {
    private Long id;
    private Integer level;
    private Type type;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate startDate;

}
