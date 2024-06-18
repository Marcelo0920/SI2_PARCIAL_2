package com.parcial2.si2.controller;

import com.parcial2.si2.model.Licencia;
import com.parcial2.si2.service.LicenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/licencias")
public class LicenciaController {

    @Autowired
    private LicenciaService licenciaService;

    @GetMapping
    public ResponseEntity<List<Licencia>> getAllLicencias() {
        List<Licencia> licencias = licenciaService.getAllLicencias();
        return new ResponseEntity<>(licencias, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Licencia> getLicenciaById(@PathVariable("id") Long id) {
        Optional<Licencia> licencia = licenciaService.getLicenciaById(id);
        return licencia.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Licencia> createLicencia(@RequestBody Licencia licencia) {
        Licencia createdLicencia = licenciaService.saveLicencia(licencia);
        return new ResponseEntity<>(createdLicencia, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLicencia(@PathVariable("id") Long id) {
        licenciaService.deleteLicencia(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}