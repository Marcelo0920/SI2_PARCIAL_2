package com.parcial2.si2.controller;

import com.parcial2.si2.dto.ComunicadoRequest;
import com.parcial2.si2.dto.ComunicadoResponse;
import com.parcial2.si2.service.ComunicadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comunicados")
public class ComunicadoController {

    @Autowired
    private ComunicadoService comunicadoService;

    @PostMapping
    public ResponseEntity<ComunicadoResponse> createComunicado(@RequestBody ComunicadoRequest request) {
        ComunicadoResponse response = comunicadoService.createComunicado(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ComunicadoResponse>> getAllComunicados() {
        List<ComunicadoResponse> comunicados = comunicadoService.getAllComunicados();
        return new ResponseEntity<>(comunicados, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComunicadoResponse> getComunicadoById(@PathVariable Long id) {
        ComunicadoResponse comunicado = comunicadoService.getComunicadoById(id);
        return new ResponseEntity<>(comunicado, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComunicadoResponse> updateComunicado(@PathVariable Long id, @RequestBody ComunicadoRequest request) {
        ComunicadoResponse updatedComunicado = comunicadoService.updateComunicado(id, request);
        return new ResponseEntity<>(updatedComunicado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComunicado(@PathVariable Long id) {
        comunicadoService.deleteComunicado(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}