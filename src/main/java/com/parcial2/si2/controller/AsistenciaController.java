package com.parcial2.si2.controller;

import com.parcial2.si2.dto.AsistenciaRequest;
import com.parcial2.si2.model.Asistencia;
import com.parcial2.si2.service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asistencia")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    @GetMapping
    public ResponseEntity<List<Asistencia>> getAllAsistencias() {
        List<Asistencia> asistencia = asistenciaService.getAllAsistencias();
        return new ResponseEntity<>(asistencia, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asistencia> getAsistenciaById(@PathVariable("id") Long id) {
        Optional<Asistencia> asistencia = asistenciaService.getAsistenciaById(id);
        return asistencia.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Asistencia> createAsistencia(@RequestBody AsistenciaRequest asistenciaRequest) {
        Asistencia savedAsistencia = asistenciaService.saveAsistencia(asistenciaRequest);
        if(savedAsistencia != null) {
            return ResponseEntity.ok(savedAsistencia);
        } else{
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsistencia(@PathVariable("id") Long id) {
        asistenciaService.deleteAsistencia(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}