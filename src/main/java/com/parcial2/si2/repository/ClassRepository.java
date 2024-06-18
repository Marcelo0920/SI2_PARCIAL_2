package com.parcial2.si2.repository;

import com.parcial2.si2.model.CourseClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<CourseClass, Long> {

    // Método derivado para buscar clases por lista de IDs
    List<CourseClass> findByIdIn(List<Long> classIds);



    // Otros métodos derivados si son necesarios

}