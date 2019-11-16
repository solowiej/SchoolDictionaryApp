package com.restapi.rest.controller;

import com.restapi.rest.model.dto.AssignGradeToStudentRequest;
import com.restapi.rest.model.Student;
import com.restapi.rest.model.dto.AddGradeToStudentRequest;
import com.restapi.rest.model.dto.StudentRequestBody;
import com.restapi.rest.model.dto.StudentDto;
import com.restapi.rest.repository.StudentRepository;
import com.restapi.rest.service.StudentService;
import com.restapi.rest.util.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

@Transactional
@RestController
public class StudentController {

    static final String API_STUDENT_PATH = "/api/student";

//    @Autowired
//    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(API_STUDENT_PATH)
    public Page<StudentDto> getPagedStudent(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                 @RequestParam(name = "pageSize", defaultValue = "5") int size){
        Page<Student> allStudent = studentRepository.findAll(PageRequest.of(page, size));

        List<StudentDto> content = allStudent
            .getContent()
            .stream()
            .map(StudentDto::newInstance)
            .collect(Collectors.toList());

        return Pagination.getPage(content, PageRequest.of(page, size), allStudent.getTotalElements());
    }

    @GetMapping(API_STUDENT_PATH+"/{id}")
    public StudentDto getStudent( @PathVariable("id") Long studentId){

        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (!studentOptional.isPresent()){
            throw new EntityNotFoundException("student, id:" + studentId);
        }
        Student student = studentOptional.get();

        return StudentDto.newInstance(student);

    }

//  ----------------  Old code bellow ------------------


//    @GetMapping("/{id}")
//    public Student getById(
//            @PathVariable("id") Long studentId) {
//        return studentService.getById(studentId);
//    }
//
//    @GetMapping("/name/{studentSurname}")
//    public List<Student> getAllStudentsBySurname(
//            @PathVariable("studentSurname") String studentSurname) {
//        return studentService.getAllBySurname(studentSurname);
//    }
//
//    @GetMapping("/ageFrom/{ageFrom}")
//    public Student getByAgeAfter(
//            @PathVariable("ageFrom") Integer ageFrom) {
//        return studentService.getByAgeAfter(ageFrom);
//    }
//
//    @GetMapping("/ageUpTo/{ageUpTo}")
//    public Student getByAgeBefore(
//            @PathVariable("ageUpTo") Integer ageUpTo) {
//        return studentService.getByAgeBefore(ageUpTo);
//    }
//
//    @GetMapping("/ageBetween/{ageFrom}/{ageUpTo}")
//    public Student getByAgeBetween(
//            @PathVariable("ageFrom") Integer ageFrom,
//            @PathVariable("ageUpTo") Integer ageUpTo) {
//        return studentService.getByAgeBetween(ageFrom, ageUpTo);
//    }
//
//    @PostMapping
//    @ResponseStatus(code = HttpStatus.ACCEPTED)
//    public StudentDto createStudent(StudentRequestBody studentRequestBody) {
//        StudentDto dto = studentService.saveStudent(studentRequestBody);
//        return dto;
//    }
//
//    @PutMapping
//    @ResponseStatus(code = HttpStatus.ACCEPTED)
//    public StudentDto updateStudent(StudentRequestBody studentRequestBody) {
//        StudentDto dto = studentService.saveStudent(studentRequestBody);
//        return dto;
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(code = HttpStatus.ACCEPTED)
//    public void delete(@PathVariable("id") Long id) {
//        studentService.delete(id);
//    }
//
//    @GetMapping("/getPage")
//    public Page<Student> getPage(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
//                                 @RequestParam(name = "pageSize", defaultValue = "5") int size) {
//        return studentService.getPage(PageRequest.of(page, size));
//    }
//
//    @PostMapping("/grade")
//    public Long addGrade(AddGradeToStudentRequest addGradeToStudent) {
//        return studentService.addGradeToStudent(addGradeToStudent);
//    }
//
//    @PostMapping("/assignGrade")
//    public Long addGrade(AssignGradeToStudentRequest dto) {
//        return studentService.assignGradeToStudent(dto);
//    }

}
