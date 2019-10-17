package com.restapi.rest.controller;

import com.restapi.rest.model.Grade;
import com.restapi.rest.model.dto.CreateGradeRequest;
import com.restapi.rest.service.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/grade")
@AllArgsConstructor
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping()
    public List<Grade> getGradeList() {
        return gradeService.getAll();
    }

    @GetMapping("/{id}")
    public Grade getById(
            @PathVariable("id") Long gradeId) {
        return gradeService.getById(gradeId);
    }

    @PutMapping
    public Long putGrade(CreateGradeRequest grade) {
        return gradeService.save(grade);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void postGrade(CreateGradeRequest grade) {
        gradeService.update(grade);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Long id) {
        gradeService.delete(id);
    }


}
