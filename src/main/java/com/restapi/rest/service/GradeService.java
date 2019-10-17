package com.restapi.rest.service;

import com.restapi.rest.mapper.GradeMapper;
import com.restapi.rest.model.Grade;
import com.restapi.rest.model.dto.CreateGradeRequest;
import com.restapi.rest.repository.GradeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private GradeMapper gradeMapper;


    public List<Grade> getAll() {
        return gradeRepository.findAll();
    }


    public Grade getById(Long gradeId) {
        Optional<Grade> optionalGrade = gradeRepository.findById(gradeId);

        if (optionalGrade.isPresent()) {
            return optionalGrade.get();
        }
        throw new EntityNotFoundException("grade, id:" + gradeId);
    }

    public Long save(CreateGradeRequest dto) {
        Grade grade = gradeMapper.createGradeFromDto(dto);

        return gradeRepository.save(grade).getId();
    }

    public void update(CreateGradeRequest gradeDto) {

        Optional<Grade> optionalGrade = gradeRepository.findById(gradeDto.getId());
        if (optionalGrade.isPresent()) {
            Grade grade = optionalGrade.get();

            if (gradeDto.getSubject() != null) {
                grade.setSubject(gradeDto.getSubject());
            }
            if (gradeDto.getValue() != null) {
                grade.setValue(gradeDto.getValue());
            }

            gradeRepository.save(grade);
            return;
        }
        throw new EntityNotFoundException("grade, id:" + gradeDto.getId());
    }

    public void delete(Long id) {
        if (gradeRepository.existsById(id)) {
            gradeRepository.deleteById(id);
            return;
        }
        throw new EntityNotFoundException("grade, id:" + id);
    }
}


