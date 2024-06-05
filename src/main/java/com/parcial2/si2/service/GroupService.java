package com.parcial2.si2.service;

import com.parcial2.si2.model.Grupo;
import com.parcial2.si2.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public List<Grupo> getAllGroups() {
        return groupRepository.findAll();
    }

    public Optional<Grupo> getGroupById(Long id) {
        return groupRepository.findById(id);
    }

    public Grupo saveGroup(Grupo group) {
        return groupRepository.save(group);
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
}