package com.parcial2.si2.service;

import com.parcial2.si2.dto.ClassroomRequest;
import com.parcial2.si2.model.Classroom;
import com.parcial2.si2.model.Module;
import com.parcial2.si2.repository.ClassroomRepository;
import com.parcial2.si2.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    public Optional<Classroom> getClassroomById(Long id) {
        return classroomRepository.findById(id);
    }

    public Classroom saveClassroom(ClassroomRequest classroomRequest) {
        Classroom classroom = new Classroom();

        Optional<Module> moduleOptional = moduleRepository.findById(classroomRequest.getModuleId());
        moduleOptional.ifPresent(classroom::setModule);

        classroom.setClassroomNumber(classroomRequest.getClassroomNumber());

        return classroomRepository.save(classroom);
    }

    public void deleteClassroom(Long id) {
        classroomRepository.deleteById(id);
    }
}