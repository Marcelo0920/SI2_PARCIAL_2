package com.parcial2.si2.controller;

import com.parcial2.si2.model.CourseClass;
import com.parcial2.si2.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/academic-program")
public class AcademicProgramController {

    @Autowired
    private ClassService classService;

    @GetMapping("/classes")
    public ResponseEntity<List<CourseClass>> getAllClasses() {
        List<CourseClass> classes = classService.getAllClasses();
        return ResponseEntity.ok(classes);
    }

    @GetMapping("/classes/faculty/{facultyId}")
    public ResponseEntity<List<CourseClass>> getClassesByFaculty(@PathVariable Long facultyId) {
        List<CourseClass> classes = classService.getClassesByFaculty(facultyId);
        return ResponseEntity.ok(classes);
    }

    @GetMapping("/classes/career/{careerId}")
    public ResponseEntity<List<CourseClass>> getClassesByCareer(@PathVariable Long careerId) {
        List<CourseClass> classes = classService.getAllClassesByCareer(careerId);
        return ResponseEntity.ok(classes);
    }

    @GetMapping("/classes/teacher/{teacherId}")
    public ResponseEntity<List<CourseClass>> getClassesByTeacherId(@PathVariable Long teacherId) {
        List<CourseClass> classes = classService.getClassesByTeacherId(teacherId);
        return ResponseEntity.ok(classes);
    }

}