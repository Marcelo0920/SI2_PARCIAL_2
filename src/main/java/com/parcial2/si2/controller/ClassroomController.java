package com.parcial2.si2.controller;

import com.parcial2.si2.dto.ClassroomRequest;
import com.parcial2.si2.model.Classroom;
import com.parcial2.si2.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @GetMapping
    public ResponseEntity<List<Classroom>> getAllClassrooms() {
        List<Classroom> classrooms = classroomService.getAllClassrooms();
        return new ResponseEntity<>(classrooms, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classroom> getClassroomById(@PathVariable("id") Long id) {
        Optional<Classroom> classroom = classroomService.getClassroomById(id);
        return classroom.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Classroom> createClassroom(@RequestBody ClassroomRequest classroomRequest) {
        Classroom savedClassroom = classroomService.saveClassroom(classroomRequest);
        if(savedClassroom != null){
            return ResponseEntity.ok(savedClassroom);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassroom(@PathVariable("id") Long id) {
        classroomService.deleteClassroom(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}