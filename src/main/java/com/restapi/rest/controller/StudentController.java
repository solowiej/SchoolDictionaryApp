package com.restapi.rest.controller;

import com.restapi.rest.model.dto.AssignGradeToStudentRequest;
import com.restapi.rest.model.Student;
import com.restapi.rest.model.dto.AddGradeToStudentRequest;
import com.restapi.rest.model.dto.CreateStudentRequest;
import com.restapi.rest.model.dto.StudentUpdateRequest;
import com.restapi.rest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping()
    public List<Student> getStudentsList() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public Student getById(
            @PathVariable("id") Long studentId) {
        return studentService.getById(studentId);
    }

    @GetMapping("/name/{studentSurname}")
    public List<Student> getAllStudentsBySurname(
            @PathVariable("studentSurname") String studentSurname) {
        return studentService.getAllBySurname(studentSurname);
    }

    @GetMapping("/ageFrom/{ageFrom}")
    public Student getByAgeAfter(
            @PathVariable("ageFrom") Integer ageFrom) {
        return studentService.getByAgeAfter(ageFrom);
    }

    @GetMapping("/ageUpTo/{ageUpTo}")
    public Student getByAgeBefore(
            @PathVariable("ageUpTo") Integer ageUpTo) {
        return studentService.getByAgeBefore(ageUpTo);
    }

    @GetMapping("/ageBetween/{ageFrom}/{ageUpTo}")
    public Student getByAgeBetween(
            @PathVariable("ageFrom") Integer ageFrom,
            @PathVariable("ageUpTo") Integer ageUpTo) {
        return studentService.getByAgeBetween(ageFrom, ageUpTo);
    }

    @PutMapping
    public Long putStudent(CreateStudentRequest student) {
        return studentService.save(student);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void postStudent(StudentUpdateRequest student) {
        studentService.update(student);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Long id) {
        studentService.delete(id);
    }

    @GetMapping("/getPage")
    public Page<Student> getPage(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                 @RequestParam(name = "pageSize", defaultValue = "5") int size) {
        return studentService.getPage(PageRequest.of(page, size));
    }

    @PostMapping("/grade")
    public Long addGrade(AddGradeToStudentRequest addGradeToStudent) {
        return studentService.addGradeToStudent(addGradeToStudent);
    }

    @PostMapping("/assignGrade")
    public Long addGrade(AssignGradeToStudentRequest dto) {
        return studentService.assignGradeToStudent(dto);
    }

}
