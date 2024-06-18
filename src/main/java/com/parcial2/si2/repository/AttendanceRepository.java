package com.parcial2.si2.repository;

import com.parcial2.si2.model.Attendance;
import com.parcial2.si2.model.CourseClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // Método derivado para buscar asistencias no registradas
    List<Attendance> findByClaseIdAndDateAndIsPresentIsNullAndLicenciaIsNull(Long classId, LocalDate date);

    List<Attendance> findByDate(LocalDate date);

    List<Attendance> findByClaseAndDate(CourseClass clase, LocalDate date);
}