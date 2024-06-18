package com.parcial2.si2.repository;

import com.parcial2.si2.model.Anotaciones;
import com.parcial2.si2.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnotacionRepository extends JpaRepository<Anotaciones, Long> {
    List<Anotaciones> findByTeacher(Teacher teacher);
    List<Anotaciones> findByTeacherIdAndCourseClassId(Long teacherId, Long courseClassId);
}