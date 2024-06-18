package com.parcial2.si2.service;

import com.parcial2.si2.dto.FaltaRequest;
import com.parcial2.si2.model.CourseClass;
import com.parcial2.si2.model.Falta;
import com.parcial2.si2.model.Teacher;
import com.parcial2.si2.repository.ClassRepository;
import com.parcial2.si2.repository.FaltaRepository;
import com.parcial2.si2.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FaltaService {

    @Autowired
    private FaltaRepository faltaRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Falta> getAllFaltas() {
        return faltaRepository.findAll();
    }

    public Optional<Falta> getFaltaById(Long id) {
        return faltaRepository.findById(id);
    }

    public Falta saveFalta(FaltaRequest faltaRequest) {
        Falta falta = new Falta();

        Optional<CourseClass> classOptional = classRepository.findById(faltaRequest.getClassId());
        classOptional.ifPresent(falta::setClase);

        Optional<Teacher> teacherOptional = teacherRepository.findById(faltaRequest.getTeacherId());
        teacherOptional.ifPresent(falta::setTeacher);

        return faltaRepository.save(falta);
    }

    public void deleteFalta(Long id) {
        faltaRepository.deleteById(id);
    }
}
