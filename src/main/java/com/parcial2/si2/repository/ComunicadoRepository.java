package com.parcial2.si2.repository;

import com.parcial2.si2.model.Comunicado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComunicadoRepository extends JpaRepository<Comunicado, Long> {

    List<Comunicado> findByFacultyId(Long facultyId);
}