package com.parcial2.si2.repository;

import com.parcial2.si2.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByUserCode(String userCode);
}