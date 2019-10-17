package com.restapi.rest.mapper;

import com.restapi.rest.model.Grade;
import com.restapi.rest.model.dto.AddGradeToStudentRequest;
import com.restapi.rest.model.dto.CreateGradeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface GradeMapper {
    Grade createGradeFromDto(CreateGradeRequest dto);

    @Mappings(value = {
            @Mapping(source = "value", target = "value"),
            @Mapping(source = "gradeSubject", target = "subject"),
            @Mapping(target = "id", ignore = true)
    })
    Grade createGradeFromDto(AddGradeToStudentRequest dto);
}
