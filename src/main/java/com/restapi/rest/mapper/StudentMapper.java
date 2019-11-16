package com.restapi.rest.mapper;

import com.restapi.rest.model.Student;
import com.restapi.rest.model.dto.AddStudentToStudentGroupRequest;
import com.restapi.rest.model.dto.StudentRequestBody;
import com.restapi.rest.model.dto.StudentDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student createStudentFromDto(StudentRequestBody dto);

    Student createStudentFromDto(StudentDto dto);

    StudentDto createStudentDtoFromStudent(Student student);

    @Mappings(value = {
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "surname", target = "surname"),
            @Mapping(source = "isAlive", target = "isAlive"),
            @Mapping(source = "dateOfBirth", target = "dateOfBirth"),
            @Mapping(target = "id", ignore = true)
    })
    Student createStudentFromDto(AddStudentToStudentGroupRequest dto);
}
