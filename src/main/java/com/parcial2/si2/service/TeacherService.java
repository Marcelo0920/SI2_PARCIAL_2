package com.parcial2.si2.service;

import com.parcial2.si2.dto.TeacherRequest;
import com.parcial2.si2.model.Career;
import com.parcial2.si2.model.Faculty;
import com.parcial2.si2.model.Teacher;
import com.parcial2.si2.repository.CareerRepository;
import com.parcial2.si2.repository.FacultyRepository;
import com.parcial2.si2.repository.TeacherRepository;
import com.parcial2.si2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private CareerRepository careerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachers() {
        return userRepository.findAll().stream()
                .filter(user -> user instanceof Teacher)
                .map(user -> (Teacher) user)
                .toList();
    }

    public Optional<Teacher> getTeacherById(Long id) {
        return userRepository.findById(id).filter(user -> user instanceof Teacher)
                .map(user -> (Teacher) user);
    }

    public Teacher saveTeacher(TeacherRequest teacherRequest) {
        Teacher teacher = new Teacher();
        teacher.setEmail(teacherRequest.getEmail());
        teacher.setName(teacherRequest.getName());
        teacher.setPhoneNumber(teacherRequest.getPhoneNumber());
        teacher.setUserCode(teacherRequest.getUserCode());
        teacher.setAge(teacherRequest.getAge());
        teacher.setEffectiveHours(teacherRequest.getEffectiveHours());
        teacher.setPassword(passwordEncoder.encode(teacherRequest.getPassword()));

        Faculty faculty = facultyRepository.findById(teacherRequest.getFacultyId())
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
        teacher.setFaculty(faculty);

        Career career = careerRepository.findById(teacherRequest.getCareerId())
                .orElseThrow(() -> new RuntimeException("Career not found"));
        teacher.setCareer(career);

        return userRepository.save(teacher);
    }

    public Teacher updateTeacher(Teacher teacher) {
        return userRepository.save(teacher);
    }

    public Teacher updateTeacherClasses(Teacher teacher) {
        return userRepository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        userRepository.deleteById(id);
    }

    //ADD CLASSES TO TEACHER
    public Teacher addClassesToTeacher(Long teacherId, List<Long> newClassIds) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        // Actualiza las clases del profesor con las nuevas clases
        List<Long> currentClasses = teacher.getClasses();
        currentClasses.addAll(newClassIds);
        teacher.setClasses(currentClasses);

        // Guarda y devuelve el profesor actualizado
        return teacherRepository.save(teacher);
    }
}