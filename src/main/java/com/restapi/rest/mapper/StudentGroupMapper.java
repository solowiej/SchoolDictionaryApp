package com.restapi.rest.mapper;

import com.restapi.rest.model.StudentGroup;
import com.restapi.rest.model.dto.CreateStudentGroupRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentGroupMapper {
    StudentGroup createStudentGroupFromDto(CreateStudentGroupRequest dto);
}
