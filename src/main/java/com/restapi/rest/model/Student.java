package com.restapi.rest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate dateOfBirth;

    @Formula(value = "(year(now())- year(date_of_birth))")
    private Integer age;

    private Boolean isAlive;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE)
    private Set<Grade> grades;

    @ManyToOne
    @JsonBackReference
    private StudentGroup studentGroup;

    /**
     * Export the current object state.
     */
    public void export(Exporter exporter) {
        exporter.setId(id);
        exporter.setName(name);
        exporter.setSurname(surname);
        exporter.setDateOfBirth(dateOfBirth);
        exporter.setIsAlive(isAlive);
        exporter.setGrades(grades);
        exporter.setStudentGroup(studentGroup);

    }
    public interface Exporter {

        void setId(Long id);

        void setName(String name);

        void setSurname(String surname);

        void setDateOfBirth(LocalDate dateOfBirth);

        void setIsAlive(Boolean isAlive);

        void setGrades(Set<Grade> grades);

        void setStudentGroup(StudentGroup studentGroup);

    }



}