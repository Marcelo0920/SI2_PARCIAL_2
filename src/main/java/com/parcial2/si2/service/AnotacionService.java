package com.parcial2.si2.service;

import com.parcial2.si2.dto.AnotacionRequest;
import com.parcial2.si2.model.Anotaciones;
import com.parcial2.si2.model.CourseClass;
import com.parcial2.si2.model.Teacher;
import com.parcial2.si2.repository.AnotacionRepository;
import com.parcial2.si2.repository.ClassRepository;
import com.parcial2.si2.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnotacionService {


    @Autowired
    private AnotacionRepository anotacionRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ClassRepository courseClassRepository;


    //GET ALL ANOTACIONES
    public List<AnotacionRequest> getAllAnotaciones() {
        return anotacionRepository.findAll().stream()
                .map(AnotacionRequest::fromEntity)
                .collect(Collectors.toList());
    }

    //GET ANOTACION BY ID
    public AnotacionRequest getAnotacionById(Long id) {
        Anotaciones anotaciones = anotacionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Anotacion with id " + id + " not found"));
        return AnotacionRequest.fromEntity(anotaciones);
    }

    //GET ANOTACIONES BY TEACHER ID
    public List<AnotacionRequest> getAnotacionesByTeacher(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalArgumentException("Teacher with id " + teacherId + " not found"));
        return anotacionRepository.findByTeacher(teacher).stream()
                .map(AnotacionRequest::fromEntity)
                .collect(Collectors.toList());
    }

    //GET ANOTACIONES BY TEACHER AND COURSE ID
    public List<AnotacionRequest> getAnotacionesByTeacherAndCourseID(Long teacherId, Long courseClassId) {
        return anotacionRepository.findByTeacherIdAndCourseClassId(teacherId, courseClassId).stream()
                .map(AnotacionRequest::fromEntity)
                .collect(Collectors.toList());
    }

    //CREATE ANOTACION
    public AnotacionRequest createAnotacion(AnotacionRequest request) {
        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new IllegalArgumentException("Teacher with id " + request.getTeacherId() + " not found"));
        CourseClass courseClass = courseClassRepository.findById(request.getCourseClassId())
                .orElseThrow(() -> new IllegalArgumentException("CourseClass with id " + request.getCourseClassId() + " not found"));

        Anotaciones anotaciones = request.toEntity(teacher, courseClass);
        Anotaciones savedAnotaciones = anotacionRepository.save(anotaciones);
        return AnotacionRequest.fromEntity(savedAnotaciones);
    }

    //UPDATE ANOTACION
    public AnotacionRequest updateAnotacion(Long id, AnotacionRequest request) {
        Anotaciones anotaciones = anotacionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Anotacion with id " + id + " not found"));

        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new IllegalArgumentException("Teacher with id " + request.getTeacherId() + " not found"));
        CourseClass courseClass = courseClassRepository.findById(request.getCourseClassId())
                .orElseThrow(() -> new IllegalArgumentException("CourseClass with id " + request.getCourseClassId() + " not found"));

        anotaciones.setTexto(request.getTexto());
        anotaciones.setTeacher(teacher);
        anotaciones.setCourseClass(courseClass);

        Anotaciones updatedAnotaciones = anotacionRepository.save(anotaciones);
        return AnotacionRequest.fromEntity(updatedAnotaciones);
    }

    //DELETE ANOTACION
    public void deleteAnotacion(Long id) {
        anotacionRepository.deleteById(id);
    }
}