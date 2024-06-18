package com.parcial2.si2.controller;

import com.parcial2.si2.model.Teacher;
import com.parcial2.si2.dto.TeacherRequest;
import com.parcial2.si2.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable("id") Long id) {
        Optional<Teacher> teacher = teacherService.getTeacherById(id);
        return teacher.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody TeacherRequest teacherRequest) {
        Teacher newTeacher = teacherService.saveTeacher(teacherRequest);
        return new ResponseEntity<>(newTeacher, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/add-classes")
    public ResponseEntity<Teacher> addClassesToTeacher(@PathVariable Long id, @RequestBody List<Long> newClassIds) {
        System.out.println("Nuevos IDs de clases: " + newClassIds);

        Teacher updatedTeacher = teacherService.addClassesToTeacher(id, newClassIds);
        return ResponseEntity.ok(updatedTeacher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody TeacherRequest teacherRequest) {
        Optional<Teacher> optionalTeacher = teacherService.getTeacherById(id);
        if (optionalTeacher.isPresent()) {
            Teacher teacher = optionalTeacher.get();
            teacher.setEmail(teacherRequest.getEmail());
            teacher.setName(teacherRequest.getName());
            teacher.setPhoneNumber(teacherRequest.getPhoneNumber());
            teacher.setUserCode(teacherRequest.getUserCode());
            teacher.setAge(teacherRequest.getAge());
            teacher.setEffectiveHours(teacherRequest.getEffectiveHours());

            // Actualizar campos adicionales
            teacher.setClasses(teacherRequest.getClasses());

            // Guardar y devolver el Teacher actualizado
            Teacher updatedTeacher = teacherService.updateTeacher(teacher);
            return ResponseEntity.ok(updatedTeacher);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/classes")
    public ResponseEntity<Teacher> updateTeacherClasses(@PathVariable Long id, @RequestBody List<Long> classes) {
        Optional<Teacher> optionalTeacher = teacherService.getTeacherById(id);
        if (optionalTeacher.isPresent()) {
            Teacher teacher = optionalTeacher.get();
            teacher.setClasses(classes);
            Teacher updatedTeacher = teacherService.updateTeacherClasses(teacher);
            return ResponseEntity.ok(updatedTeacher);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable("id") Long id) {
        teacherService.deleteTeacher(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}