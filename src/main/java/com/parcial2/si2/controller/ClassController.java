package com.parcial2.si2.controller;

import com.parcial2.si2.dto.ClassRequest;
import com.parcial2.si2.model.CourseClass;
import com.parcial2.si2.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/classes")
public class ClassController {

    @Autowired
    private ClassService classService;

    @GetMapping
    public ResponseEntity<List<CourseClass>> getAllClasses() {
        List<CourseClass> courseClasses = classService.getAllClasses();
        return new ResponseEntity<>(courseClasses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseClass> getClassById(@PathVariable("id") Long id) {
        Optional<CourseClass> clase = classService.getClassById(id);
        return clase.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CourseClass> createClass(@RequestBody ClassRequest classRequest) {
        CourseClass savedCourseClass = classService.saveClass(classRequest);
        if (savedCourseClass != null) {
            return ResponseEntity.ok(savedCourseClass);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseClass> updateClass(@PathVariable("id") Long id, @RequestBody ClassRequest classRequest) {
        CourseClass updatedCourseClass = classService.updateClass(id, classRequest);
        if (updatedCourseClass != null) {
            return ResponseEntity.ok(updatedCourseClass);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable("id") Long id) {
        classService.deleteClass(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}