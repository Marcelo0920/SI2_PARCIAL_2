package com.parcial2.si2.service;

import com.parcial2.si2.dto.ModuleRequest;
import com.parcial2.si2.model.Faculty;
import com.parcial2.si2.model.Module;
import com.parcial2.si2.repository.FacultyRepository;
import com.parcial2.si2.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    public Optional<Module> getModuleById(Long id) {
        return moduleRepository.findById(id);
    }



    public Optional<Module> createModule(ModuleRequest moduleRequest) {
        return facultyRepository.findById(moduleRequest.getFacultyId())
                .map(faculty -> {
                    Module module = new Module();
                    module.setModuleNumber(moduleRequest.getModuleNumber());
                    module.setFaculty(faculty);
                    return moduleRepository.save(module);
                });
    }

    public Optional<Module> updateModule(Long id, ModuleRequest moduleRequest) {
        return moduleRepository.findById(id).flatMap(module -> facultyRepository.findById(moduleRequest.getFacultyId())
                .map(faculty -> {
                    module.setModuleNumber(moduleRequest.getModuleNumber());
                    module.setFaculty(faculty);
                    return moduleRepository.save(module);
                }));
    }

    public boolean deleteModule(Long id) {
        return moduleRepository.findById(id)
                .map(module -> {
                    moduleRepository.delete(module);
                    return true;
                }).orElse(false);
    }
}