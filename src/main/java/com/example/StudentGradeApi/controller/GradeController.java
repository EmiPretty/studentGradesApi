package com.example.StudentGradeApi.controller;

import com.example.StudentGradeApi.model.Grade;
import com.example.StudentGradeApi.repository.CourseRepository;
import com.example.StudentGradeApi.repository.GradeRepository;
import com.example.StudentGradeApi.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/grades")
public class GradeController {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    // Enregistrer une nouvelle note
    @PostMapping
    public ResponseEntity<Grade> addGrade(@Valid @RequestBody Grade grade) {
        if (!studentRepository.existsById(grade.getStudentId()) || !courseRepository.existsById(grade.getCourseId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Grade> existingGrade = gradeRepository.findByStudentIdAndCourseId(grade.getStudentId(), grade.getCourseId());
        if (!existingGrade.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Grade savedGrade = gradeRepository.save(grade);
        return new ResponseEntity<>(savedGrade, HttpStatus.CREATED);
    }

    // Mettre à jour une note
    @PutMapping("/{id}")
    public ResponseEntity<Grade> updateGrade(@PathVariable Long id, @Valid @RequestBody Grade updatedGrade) {
        Optional<Grade> existingGrade = gradeRepository.findById(id);
        if (!existingGrade.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Si la note n'existe pas
        }

        if (updatedGrade.getStudentId() != null && !studentRepository.existsById(updatedGrade.getStudentId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (updatedGrade.getCourseId() != null && !courseRepository.existsById(updatedGrade.getCourseId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        updatedGrade.setId(id);
        Grade savedGrade = gradeRepository.save(updatedGrade);
        return new ResponseEntity<>(savedGrade, HttpStatus.OK);
    }

    // Supprimer une note
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        if (gradeRepository.existsById(id)) {
            gradeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Récupérer les notes d'un étudiant
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Grade>> getGradesByStudent(@PathVariable Long studentId) {
        List<Grade> grades = gradeRepository.findByStudentId(studentId);
        return new ResponseEntity<>(grades, HttpStatus.OK);
    }

    // Récupérer les notes pour un cours
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Grade>> getGradesByCourse(@PathVariable Long courseId) {
        List<Grade> grades = gradeRepository.findByCourseId(courseId);
        return new ResponseEntity<>(grades, HttpStatus.OK);
    }
}