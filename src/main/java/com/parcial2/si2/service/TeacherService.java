package com.parcial2.si2.service;

import com.parcial2.si2.model.Career;
import com.parcial2.si2.model.Faculty;
import com.parcial2.si2.model.Teacher;
import com.parcial2.si2.repository.CareerRepository;
import com.parcial2.si2.repository.FacultyRepository;
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

    public Teacher saveTeacher(String email, String name, String phoneNumber, String userCode, int age, float effectiveHours, String password, Long facultyId, Long careerId) {
        Teacher teacher = new Teacher();
        teacher.setEmail(email);
        teacher.setName(name);
        teacher.setPhoneNumber(phoneNumber);
        teacher.setUserCode(userCode);
        teacher.setAge(age);
        teacher.setEffectiveHours(effectiveHours);
        teacher.setPassword(passwordEncoder.encode(password));


        Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(() -> new RuntimeException("Faculty not found"));
        Career career = careerRepository.findById(careerId).orElseThrow(() -> new RuntimeException("Career not found"));

        teacher.setFaculty(faculty);
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
}