package com.parcial2.si2.repository;

import com.parcial2.si2.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Grupo, Long> {
}