package com.parcial2.si2.service;

import com.parcial2.si2.dto.ComunicadoRequest;
import com.parcial2.si2.dto.ComunicadoResponse;
import com.parcial2.si2.model.Comunicado;
import com.parcial2.si2.model.Faculty;
import com.parcial2.si2.repository.ComunicadoRepository;
import com.parcial2.si2.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComunicadoService {

    @Autowired
    private ComunicadoRepository comunicadoRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    //GET COMUNICADOS BY FACULTY ID
    public List<ComunicadoResponse> getComunicadosByFacultyId(Long facultyId) {
        List<Comunicado> comunicados = comunicadoRepository.findByFacultyId(facultyId);
        return comunicados.stream()
                .map(ComunicadoResponse::fromEntity)
                .collect(Collectors.toList());
    }

    //GET ALL COMUNICADOS
    public List<ComunicadoResponse> getAllComunicados() {
        List<Comunicado> comunicados = comunicadoRepository.findAll();
        return comunicados.stream()
                .map(ComunicadoResponse::fromEntity)
                .collect(Collectors.toList());
    }

    //GET COMUNICADO BY ID
    public ComunicadoResponse getComunicadoById(Long id) {
        Comunicado comunicado = comunicadoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comunicado with id " + id + " not found"));
        return ComunicadoResponse.fromEntity(comunicado);
    }

    public ComunicadoResponse createComunicado(ComunicadoRequest request) {
        Faculty faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new IllegalArgumentException("Faculty with id " + request.getFacultyId() + " not found"));

        Comunicado comunicado = request.toEntity();
        comunicado.setFaculty(faculty);

        Comunicado savedComunicado = comunicadoRepository.save(comunicado);
        return ComunicadoResponse.fromEntity(savedComunicado);
    }



    public ComunicadoResponse updateComunicado(Long id, ComunicadoRequest request) {
        Comunicado comunicado = comunicadoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comunicado with id " + id + " not found"));

        Faculty faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new IllegalArgumentException("Faculty with id " + request.getFacultyId() + " not found"));

        comunicado.setTitulo(request.getTitulo());
        comunicado.setImageUrl(request.getImageUrl());
        comunicado.setFaculty(faculty);

        Comunicado updatedComunicado = comunicadoRepository.save(comunicado);
        return ComunicadoResponse.fromEntity(updatedComunicado);
    }

    public void deleteComunicado(Long id) {
        comunicadoRepository.deleteById(id);
    }
}