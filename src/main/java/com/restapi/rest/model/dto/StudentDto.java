package com.restapi.rest.model.dto;

import com.restapi.rest.model.Grade;
import com.restapi.rest.model.StudentGroup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

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
}
