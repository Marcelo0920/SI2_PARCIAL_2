package com.parcial2.si2.service;

import com.parcial2.si2.dto.AsistenciaRequest;
import com.parcial2.si2.model.Asistencia;
import com.parcial2.si2.model.Class;
import com.parcial2.si2.model.Teacher;
import com.parcial2.si2.repository.AsistenciaRepository;
import com.parcial2.si2.repository.ClassRepository;
import com.parcial2.si2.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Asistencia> getAllAsistencias() {
        return asistenciaRepository.findAll();
    }

    public Optional<Asistencia> getAsistenciaById(Long id) {
        return asistenciaRepository.findById(id);
    }

    public Asistencia saveAsistencia(AsistenciaRequest asistenciaRequest) {
        Asistencia asistencia = new Asistencia();

        Optional<Class> classOptional = classRepository.findById(asistenciaRequest.getClassId());
        classOptional.ifPresent(asistencia::setClase);

        Optional<Teacher> teacherOptional = teacherRepository.findById(asistenciaRequest.getTeacherId());
        teacherOptional.ifPresent(asistencia::setTeacher);

        return asistenciaRepository.save(asistencia);
    }


    public void deleteAsistencia(Long id) {
        asistenciaRepository.deleteById(id);
    }
}