package com.parcial2.si2.service;

import com.parcial2.si2.model.Usuario;
import com.parcial2.si2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;

    public List<Usuario> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<Usuario> getUserById(Long id){
        return userRepository.findById(id);
    }

    public Usuario saveUser(Usuario user){
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}