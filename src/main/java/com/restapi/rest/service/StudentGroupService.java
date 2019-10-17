package com.restapi.rest.service;

import com.restapi.rest.exception.WrongOperation;
import com.restapi.rest.mapper.StudentGroupMapper;
import com.restapi.rest.mapper.StudentMapper;
import com.restapi.rest.model.Student;
import com.restapi.rest.model.StudentGroup;
import com.restapi.rest.model.dto.AddStudentToStudentGroupRequest;
import com.restapi.rest.model.dto.AssignStudentToStudentGroupRequest;
import com.restapi.rest.model.dto.CreateStudentGroupRequest;
import com.restapi.rest.repository.StudentGroupRepository;
import com.restapi.rest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class StudentGroupService {
    private StudentGroupRepository studentGroupRepository;
    private StudentGroupMapper studentGroupMapper;
    private StudentMapper studentMapper;
    private StudentRepository studentRepository;

    @Autowired
    public StudentGroupService(StudentGroupRepository studentGroupRepository, StudentGroupMapper studentGroupMapper, StudentMapper studentMapper, StudentRepository studentRepository) {
        this.studentGroupRepository = studentGroupRepository;
        this.studentGroupMapper = studentGroupMapper;
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
    }

    public List<StudentGroup> getAll() {
        return studentGroupRepository.findAll();
    }


    public StudentGroup getById(Long studentGroupId) {
        Optional<StudentGroup> optionalStudentGroup = studentGroupRepository.findById(studentGroupId);

        if (optionalStudentGroup.isPresent()) {
            return optionalStudentGroup.get();
        }
        throw new EntityNotFoundException("studentGroup, id:" + studentGroupId);
    }

    public Long save(CreateStudentGroupRequest dto) {
        StudentGroup studentGroup = studentGroupMapper.createStudentGroupFromDto(dto);

        return studentGroupRepository.save(studentGroup).getId();
    }

    public void update(CreateStudentGroupRequest dto) {

        Optional<StudentGroup> optionalStudentGroup = studentGroupRepository.findById(dto.getId());
        if (optionalStudentGroup.isPresent()) {
            StudentGroup studentGroup = optionalStudentGroup.get();

            if (dto.getLevel() != null) {
                studentGroup.setLevel(dto.getLevel());
            }
            if (dto.getType() != null) {
                studentGroup.setType(dto.getType());
            }

            studentGroupRepository.save(studentGroup);
            return;
        }
        throw new EntityNotFoundException("studentGroup, id:" + dto.getId());
    }

    public void delete(Long id) {
        if (studentGroupRepository.existsById(id)) {
            studentGroupRepository.deleteById(id);
            return;
        }
        throw new EntityNotFoundException("studentGroup, id:" + id);
    }

    public Long addStudentToStudentGroup(AddStudentToStudentGroupRequest addStudentToStudentGroup) {

        Optional<StudentGroup> optionalStudentGroup = studentGroupRepository.findById(addStudentToStudentGroup.getStudentGroupId());

        if (optionalStudentGroup.isPresent()) {
            StudentGroup studentGroup = optionalStudentGroup.get();

            Student student = studentMapper.createStudentFromDto(addStudentToStudentGroup);
            student.setStudentGroup(studentGroup);

            return studentRepository.save(student).getId();
        }
        throw new EntityNotFoundException("studentGroup, id:" + addStudentToStudentGroup.getStudentGroupId());
    }

    public Long assingStudentToStudentGroup(AssignStudentToStudentGroupRequest dto) {
        Optional<StudentGroup> optionalStudentGroup = studentGroupRepository.findById(dto.getStudentId());
        if (!optionalStudentGroup.isPresent()) {
            throw new EntityNotFoundException("studentGroup, id:" + dto.getStudentId());
        }
        Optional<Student> optionalStudent = studentRepository.findById(dto.getStudentId());
        if (!optionalStudent.isPresent()) {
            throw new EntityNotFoundException("student, id:" + dto.getStudentId());
        }

        StudentGroup studentGroup = optionalStudentGroup.get();
        Student student = optionalStudent.get();

        if (student.getStudentGroup() != null) {
            throw new WrongOperation("You should not assign student that is already assigned." +
                    "Use/reassign mapping.");
        }
        student.setStudentGroup(studentGroup);
        return studentRepository.save(student).getId();
    }


}
