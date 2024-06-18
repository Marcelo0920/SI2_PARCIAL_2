package com.parcial2.si2.controller;

import com.parcial2.si2.dto.CareerRequest;
import com.parcial2.si2.model.Career;
import com.parcial2.si2.service.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/careers")
public class CareerController {

    @Autowired
    private CareerService careerService;



    @GetMapping
    public ResponseEntity<List<Career>> getAllCareers() {
        List<Career> careers = careerService.getAllCareers();
        return new ResponseEntity<>(careers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Career> getCareerById(@PathVariable("id") Long id) {
        Optional<Career> career = careerService.getCareerById(id);
        return career.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Career> createCareer(@RequestBody CareerRequest careerRequest) {
        Career createdCareer = careerService.saveCareer(careerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCareer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCareer(@PathVariable("id") Long id) {
        boolean isDeleted = careerService.deleteCareer(id);

            return new ResponseEntity<>("El registro se eliminó con éxito", HttpStatus.OK);
        }
    }

