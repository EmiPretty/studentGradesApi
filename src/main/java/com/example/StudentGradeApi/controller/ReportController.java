package com.example.StudentGradeApi.controller;

import com.example.StudentGradeApi.model.Grade;
import com.example.StudentGradeApi.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private GradeRepository gradeRepository;

    //  Générer un rapport des notes moyennes pour un cours
    @GetMapping("/course/{courseId}")
    public ResponseEntity<Double> getAverageGradeByCourse(@PathVariable Long courseId) {
        List<Grade> grades = gradeRepository.findByCourseId(courseId);
        if (grades.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        double average = grades.stream()
                .mapToDouble(Grade::getGrade)
                .average()
                .orElse(0.0);
        return new ResponseEntity<>(average, HttpStatus.OK);
    }

    //  Générer un rapport des notes moyennes pour un étudiant
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Double> getAverageGradeByStudent(@PathVariable Long studentId) {
        List<Grade> grades = gradeRepository.findByStudentId(studentId);
        if (grades.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        double average = grades.stream()
                .mapToDouble(Grade::getGrade)
                .average()
                .orElse(0.0);
        return new ResponseEntity<>(average, HttpStatus.OK);
    }
}
