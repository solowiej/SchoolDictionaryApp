package com.restapi.rest.repository;

import com.restapi.rest.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllBySurname(String surname);

    Optional<Student> findByAgeBefore(Integer year);

    Optional<Student> findByAgeAfter(Integer year);

    Optional<Student> findByAgeBetween(Integer from, Integer upTo);

}
