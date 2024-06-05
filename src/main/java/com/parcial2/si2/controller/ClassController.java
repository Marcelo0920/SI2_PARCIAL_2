package com.parcial2.si2.controller;

import com.parcial2.si2.dto.ClassRequest;
import com.parcial2.si2.model.Class;
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
    public ResponseEntity<List<Class>> getAllClasses() {
        List<Class> classes = classService.getAllClasses();
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Class> getClassById(@PathVariable("id") Long id) {
        Optional<Class> clase = classService.getClassById(id);
        return clase.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Class> createClass(@RequestBody ClassRequest classRequest) {
        Class savedClass = classService.saveClass(classRequest);
        if (savedClass != null) {
            return ResponseEntity.ok(savedClass);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Class> updateClass(@PathVariable("id") Long id, @RequestBody ClassRequest classRequest) {
        Class updatedClass = classService.updateClass(id, classRequest);
        if (updatedClass != null) {
            return ResponseEntity.ok(updatedClass);
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