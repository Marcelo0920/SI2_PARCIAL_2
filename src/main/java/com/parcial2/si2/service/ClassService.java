package com.parcial2.si2.service;

import com.parcial2.si2.dto.ClassRequest;
import com.parcial2.si2.model.Class;
import com.parcial2.si2.model.Classroom;
import com.parcial2.si2.model.Grupo;
import com.parcial2.si2.model.Subject;
import com.parcial2.si2.repository.ClassRepository;
import com.parcial2.si2.repository.ClassroomRepository;
import com.parcial2.si2.repository.GroupRepository;
import com.parcial2.si2.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private GroupRepository groupRepository;

    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    public Optional<Class> getClassById(Long id) {
        return classRepository.findById(id);
    }

    public Class saveClass(ClassRequest classRequest) {
        Class clase = new Class();

        Optional<Subject> subjectOptional = subjectRepository.findById(classRequest.getSubjectId());
        subjectOptional.ifPresent(clase::setSubject);

        Optional<Grupo> grupoOptional = groupRepository.findById(classRequest.getGrupoId());
        grupoOptional.ifPresent(clase::setGroup);

        Optional<Classroom> classroomOptional = classroomRepository.findById(classRequest.getClassroomId());
        classroomOptional.ifPresent(clase::setClassroom);

       return classRepository.save(clase);
    }

    public Class updateClass(Long id, ClassRequest classRequest) {
        Optional<Class> existingClassOptional = classRepository.findById(id);
        if (existingClassOptional.isPresent()) {
            Class existingClass = existingClassOptional.get();


            Optional<Classroom> classroomOptional = classroomRepository.findById(classRequest.getClassroomId());

            if (classroomOptional.isPresent()) {
                // Si existe el aula con el ID proporcionado, establecerla en la clase existente
                existingClass.setClassroom(classroomOptional.get());

                // Guardar los cambios
                return classRepository.save(existingClass);
            }
        }
        return null;
    }

    public void deleteClass(Long id) {
        classRepository.deleteById(id);
    }
}