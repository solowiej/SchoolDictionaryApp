package com.restapi.rest.model.dto;

import com.restapi.rest.model.Grade;
import com.restapi.rest.model.Student;
import com.restapi.rest.model.StudentGroup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto implements Student.Exporter{

  private Long id;

  private String name;

  private String surname;

  private LocalDate dateOfBirth;

  private Boolean isAlive;

  private Set<Grade> grades;

  private StudentGroup studentGroup;

  public StudentDto(String name, String surname, LocalDate dateOfBirth, Boolean isAlive) {
    this.name = name;
    this.surname = surname;
    this.dateOfBirth = dateOfBirth;
    this.isAlive = isAlive;
  }

  /**
   * Creates new instance based on domain object.
   */

  public static StudentDto newInstance(Student student) {
    StudentDto dto = new StudentDto();
    student.export(dto);
    return dto;
  }
}
