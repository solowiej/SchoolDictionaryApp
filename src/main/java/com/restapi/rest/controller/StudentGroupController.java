package com.restapi.rest.controller;

import com.restapi.rest.model.StudentGroup;
import com.restapi.rest.model.dto.AddStudentToStudentGroupRequest;
import com.restapi.rest.model.dto.AssignStudentToStudentGroupRequest;
import com.restapi.rest.model.dto.CreateStudentGroupRequest;
import com.restapi.rest.service.StudentGroupService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/studentGroup")
@AllArgsConstructor
public class StudentGroupController {

    @Autowired
    private StudentGroupService studentGroupService;

    @GetMapping()
    public List<StudentGroup> getStudentGroupList() {
        return studentGroupService.getAll();
    }

    @GetMapping("/{id}")
    public StudentGroup getById(
            @PathVariable("id") Long studentGroupId) {
        return studentGroupService.getById(studentGroupId);
    }

    @PutMapping
    public Long putStudentGroup(CreateStudentGroupRequest studentGroup) {
        return studentGroupService.save(studentGroup);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void postStudentGroup(CreateStudentGroupRequest studentGroup) {
        studentGroupService.update(studentGroup);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Long id) {
        studentGroupService.delete(id);
    }

    @PostMapping("/student")
    public Long addStudent(AddStudentToStudentGroupRequest addStudentToStudentGroup) {
        return studentGroupService.addStudentToStudentGroup(addStudentToStudentGroup);
    }

    @PostMapping("/assignStudent")
    public Long addStudent(AssignStudentToStudentGroupRequest dto) {
        return studentGroupService.assingStudentToStudentGroup(dto);
    }

}
