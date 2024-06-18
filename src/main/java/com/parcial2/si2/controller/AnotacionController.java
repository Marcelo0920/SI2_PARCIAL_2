package com.parcial2.si2.controller;

import com.parcial2.si2.dto.AnotacionFilterRequest;
import com.parcial2.si2.dto.AnotacionRequest;
import com.parcial2.si2.service.AnotacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anotaciones")
public class AnotacionController {

    @Autowired
    private AnotacionService anotacionService;

    @GetMapping
    public ResponseEntity<List<AnotacionRequest>> getAllAnotaciones() {
        List<AnotacionRequest> anotaciones = anotacionService.getAllAnotaciones();
        return new ResponseEntity<>(anotaciones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnotacionRequest> getAnotacionById(@PathVariable Long id) {
        AnotacionRequest anotacion = anotacionService.getAnotacionById(id);
        return new ResponseEntity<>(anotacion, HttpStatus.OK);
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<AnotacionRequest>> getAnotacionesByTeacher(@PathVariable Long teacherId) {
        List<AnotacionRequest> anotaciones = anotacionService.getAnotacionesByTeacher(teacherId);
        return new ResponseEntity<>(anotaciones, HttpStatus.OK);
    }

    @PostMapping("/by-teacher-and-course")
    public ResponseEntity<List<AnotacionRequest>> getAnotacionesByTeacherAndCourseID(@RequestBody AnotacionFilterRequest filterRequest) {
        List<AnotacionRequest> anotaciones = anotacionService.getAnotacionesByTeacherAndCourseID(
                filterRequest.getTeacherId(),
                filterRequest.getCourseClassId()
        );
        return new ResponseEntity<>(anotaciones, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AnotacionRequest> createAnotacion(@RequestBody AnotacionRequest request) {
        AnotacionRequest anotacion = anotacionService.createAnotacion(request);
        return new ResponseEntity<>(anotacion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnotacionRequest> updateAnotacion(@PathVariable Long id, @RequestBody AnotacionRequest request) {
        AnotacionRequest updatedAnotacion = anotacionService.updateAnotacion(id, request);
        return new ResponseEntity<>(updatedAnotacion, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnotacion(@PathVariable Long id) {
        anotacionService.deleteAnotacion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}