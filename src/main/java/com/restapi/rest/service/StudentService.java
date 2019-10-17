package com.restapi.rest.service;

import com.restapi.rest.exception.WrongOperation;
import com.restapi.rest.mapper.GradeMapper;
import com.restapi.rest.mapper.StudentMapper;
import com.restapi.rest.model.Grade;
import com.restapi.rest.model.Student;
import com.restapi.rest.model.dto.AddGradeToStudentRequest;
import com.restapi.rest.model.dto.AssignGradeToStudentRequest;
import com.restapi.rest.model.dto.CreateStudentRequest;
import com.restapi.rest.model.dto.StudentUpdateRequest;
import com.restapi.rest.repository.GradeRepository;
import com.restapi.rest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private GradeRepository gradeRepository;
    private StudentMapper studentMapper;
    private GradeMapper gradeMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository, GradeRepository gradeRepository, StudentMapper studentMapper, GradeMapper gradeMapper) {
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
        this.studentMapper = studentMapper;
        this.gradeMapper = gradeMapper;
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }


    public Student getById(Long studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        if (optionalStudent.isPresent()) {
            return optionalStudent.get();
        }
        throw new EntityNotFoundException("student, id:" + studentId);
    }


    public List<Student> getAllBySurname(String studentSurname) {
        return studentRepository.findAllBySurname(studentSurname);
    }

    public Student getByAgeBefore(Integer age) {
        Optional<Student> optionalStudent = studentRepository
                .findByAgeBefore(age);

        if (optionalStudent.isPresent()) {
            return optionalStudent.get();
        }
        throw new EntityNotFoundException("student, ageBefore:" + age);
    }

    public Student getByAgeAfter(Integer age) {
        Optional<Student> optionalStudent = studentRepository
                .findByAgeAfter(age);

        if (optionalStudent.isPresent()) {
            return optionalStudent.get();
        }
        throw new EntityNotFoundException("student, ageAfter:" + age);
    }

    public Student getByAgeBetween(Integer from, Integer upTo) {
        Optional<Student> optionalStudent = studentRepository
                .findByAgeBetween(from, upTo);

        if (optionalStudent.isPresent()) {
            return optionalStudent.get();
        }
        throw new EntityNotFoundException("student, Between:" + from + "and" + upTo);
    }


    public Long save(CreateStudentRequest dto) {
        Student student = studentMapper.createStudentFromDto(dto);

        return studentRepository.save(student).getId();
    }

    public void update(StudentUpdateRequest studentToEdit) {
        Optional<Student> optionalStudent = studentRepository.findById(studentToEdit.getId());
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();

            if (studentToEdit.getName() != null) {
                student.setName(studentToEdit.getName());
            }
            if (studentToEdit.getSurname() != null) {
                student.setSurname(studentToEdit.getSurname());
            }
            if (studentToEdit.getDateOfBirth() != null) {
                student.setDateOfBirth(studentToEdit.getDateOfBirth());
            }
            if (studentToEdit.getIsAlive() != null) {
                student.setIsAlive(student.getIsAlive());
            }

            studentRepository.save(student);
            return;
        }
        throw new EntityNotFoundException("student, id:" + studentToEdit.getId());
    }

    public void delete(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return;
        }
        throw new EntityNotFoundException("student, id:" + id);
    }

    public Page<Student> getPage(PageRequest of) {
        return studentRepository.findAll(of);
    }


    public Long addGradeToStudent(AddGradeToStudentRequest addGradeToStudent) {

        Optional<Student> studentOptional = studentRepository.findById(addGradeToStudent.getStudentId());

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();

            Grade grade = gradeMapper.createGradeFromDto(addGradeToStudent);
            grade.setStudent(student);

            return gradeRepository.save(grade).getId();
        }
        throw new EntityNotFoundException("student, id:" + addGradeToStudent.getStudentId());
    }

    public Long assignGradeToStudent(AssignGradeToStudentRequest dto) {
        Optional<Student> studentOptional = studentRepository.findById(dto.getStudentId());
        if (!studentOptional.isPresent()) {
            throw new EntityNotFoundException("student, id:" + dto.getStudentId());
        }
        Optional<Grade> optionalGrade = gradeRepository.findById(dto.getGradeId());
        if (!optionalGrade.isPresent()) {
            throw new EntityNotFoundException("grade, id" + dto.getGradeId());
        }

        Student student = studentOptional.get();
        Grade grade = optionalGrade.get();

        if (grade.getStudent() != null) {
            throw new WrongOperation("You should not assign grade that is already assigned." +
                    "Use/reassign mapping.");
        }
        grade.setStudent(student);

        return gradeRepository.save(grade).getId();
    }
}
