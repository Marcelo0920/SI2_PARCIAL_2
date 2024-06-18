package com.parcial2.si2.service;

import com.parcial2.si2.dto.CareerRequest;
import com.parcial2.si2.model.Career;
import com.parcial2.si2.model.Faculty;
import com.parcial2.si2.repository.CareerRepository;
import com.parcial2.si2.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CareerService {

    @Autowired
    private CareerRepository careerRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    public List<Career> getAllCareers() {
        return careerRepository.findAll();
    }

    public Optional<Career> getCareerById(Long id) {
        return careerRepository.findById(id);
    }

    public Career saveCareer(CareerRequest careerRequest) {
        // Buscar la facultad por su ID
        Optional<Faculty> facultyOptional = facultyRepository.findById(careerRequest.getFacultyId());
        if (facultyOptional.isEmpty()) {
            throw new RuntimeException("Faculty not found with ID: " + careerRequest.getFacultyId());
        }

        // Crear una nueva carrera y establecer la facultad
        Career career = new Career();
        career.setName(careerRequest.getName());
        career.setSigla(careerRequest.getSigla());
        career.setFaculty(facultyOptional.get());

        // Guardar la carrera en la base de datos
        return careerRepository.save(career);
    }

    public boolean deleteCareer(Long id) {
        return careerRepository.findById(id)
                .map(career -> {
                    careerRepository.delete(career);
                    return true;
                }).orElse(false);
    }
}