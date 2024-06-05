package com.parcial2.si2.controller;

import com.parcial2.si2.dto.FaltaRequest;
import com.parcial2.si2.model.Falta;
import com.parcial2.si2.service.FaltaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/faltas")
public class FaltaController {

    @Autowired
    private FaltaService faltaService;

    @GetMapping
    public ResponseEntity<List<Falta>> getAllFaltas() {
        List<Falta> faltas = faltaService.getAllFaltas();
        return new ResponseEntity<>(faltas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Falta> getFaltaById(@PathVariable("id") Long id) {
        Optional<Falta> falta = faltaService.getFaltaById(id);
        return falta.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Falta> createFalta(@RequestBody FaltaRequest faltaRequest) {
        Falta savedFalta = faltaService.saveFalta(faltaRequest);
        if(savedFalta != null){
            return ResponseEntity.ok(savedFalta);
        } else{
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFalta(@PathVariable("id") Long id) {
        faltaService.deleteFalta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}