package com.parcial2.si2.service;

import com.parcial2.si2.dto.ClassRequest;
import com.parcial2.si2.model.*;
import com.parcial2.si2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private CareerRepository careerRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    //OBTENER TODAS LAS CLASES
    public List<CourseClass> getAllClasses() {
        return classRepository.findAll();
    }

    //OBTENER CLASES POR TEAACHER ID
    public List<CourseClass> getClassesByTeacherId(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        List<Long> classIds = teacher.getClasses();
        return classRepository.findAllById(classIds);
    }

    //OBTENER CLASE POR ID
    public Optional<CourseClass> getClassById(Long id) {
        return classRepository.findById(id);
    }

    // OBTENER TODAS LAS CLASES POR CARRERA
    public List<CourseClass> getAllClassesByCareer(Long careerId) {
        return classRepository.findAll().stream()
                .filter(courseClass -> courseClass.getCareer() != null && courseClass.getCareer().getId().equals(careerId))
                .collect(Collectors.toList());
    }

    // OBTENER TODAS LAS CLASES POR FACULTAD
    public List<CourseClass> getClassesByFaculty(Long facultyId) {
        // Buscar todos los profesores por facultad
        List<Teacher> teachers = teacherRepository.findByFacultyId(facultyId);

        // Obtener todas las clases a partir de los profesores encontrados
        return teachers.stream()
                .flatMap(teacher -> teacher.getClasses().stream()) // Obtener IDs de clases de cada profesor
                .map(classId -> classRepository.findById(classId)) // Buscar cada clase por ID
                .filter(Optional::isPresent) // Filtrar clases encontradas
                .map(Optional::get) // Obtener el objeto CourseClass
                .collect(Collectors.toList()); // Recolectar en una lista
    }

    //CREAR CLASE
    public CourseClass saveClass(ClassRequest classRequest) {
        CourseClass clase = new CourseClass();

        Optional<Subject> subjectOptional = subjectRepository.findById(classRequest.getSubjectId());
        subjectOptional.ifPresent(clase::setSubject);

        Optional<Grupo> grupoOptional = groupRepository.findById(classRequest.getGrupoId());
        grupoOptional.ifPresent(clase::setGroup);

        Optional<Career> careerOptional = careerRepository.findById(classRequest.getCareerId());
        careerOptional.ifPresent(clase::setCareer);

        Optional<Classroom> classroomOptional = classroomRepository.findById(classRequest.getClassroomId());
        classroomOptional.ifPresent(clase::setClassroom);

        clase.setHorarioEntrada(classRequest.getHorarioEntrada());
        clase.setHorarioSalida(classRequest.getHorarioSalida());
        clase.setDias(classRequest.getDias());
        clase.setUbicacion(classRequest.getUbicacion());


       return classRepository.save(clase);
    }

    //ACTUALIZAR CLASE
    public CourseClass updateClass(Long id, ClassRequest classRequest) {
        Optional<CourseClass> existingClassOptional = classRepository.findById(id);
        if (existingClassOptional.isPresent()) {
            CourseClass existingCourseClass = existingClassOptional.get();


            Optional<Classroom> classroomOptional = classroomRepository.findById(classRequest.getClassroomId());

            if (classroomOptional.isPresent()) {
                // Si existe el aula con el ID proporcionado, establecerla en la clase existente
                existingCourseClass.setClassroom(classroomOptional.get());

                // Guardar los cambios
                return classRepository.save(existingCourseClass);
            }
        }
        return null;
    }

    //ELIMINAR CLASE
    public void deleteClass(Long id) {
        classRepository.deleteById(id);
    }
}