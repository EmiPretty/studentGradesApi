package com.example.StudentGradeApi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"student_id", "course_id"})})
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "L'ID de l'étudiant est obligatoire")
    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @NotNull(message = "L'ID du cours est obligatoire")
    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @NotNull(message = "La note est obligatoire")
    @Min(value = 0, message = "La note doit être au minimum 0")
    @Max(value = 20, message = "La note doit être au maximum 20")
    @Column(nullable = false)
    private Double grade;
}