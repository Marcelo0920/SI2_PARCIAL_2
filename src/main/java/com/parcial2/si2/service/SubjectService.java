package com.parcial2.si2.service;

import com.parcial2.si2.dto.SubjectRequest;
import com.parcial2.si2.model.Subject;
import com.parcial2.si2.repository.CareerRepository;
import com.parcial2.si2.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private CareerRepository careerRepository;

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Optional<Subject> getSubjectById(Long id) {
        return subjectRepository.findById(id);
    }

    public Optional<Subject> saveSubject(SubjectRequest subjectRequest) {
        return careerRepository.findById(subjectRequest.getCareerId())
                .map(career -> {
                    Subject subject = new Subject();
                    subject.setName(subjectRequest.getName());
                    subject.setSigla(subjectRequest.getSigla());
                    subject.setCareer(career);
                    return subjectRepository.save(subject);
                });
    }

    public Optional<Subject> updateSubject(Long id, SubjectRequest subjectRequest){
        return subjectRepository.findById(id).flatMap(subject -> careerRepository.findById(subjectRequest.getCareerId())
                .map(career -> {
                    subject.setName(subjectRequest.getName());
                    subject.setSigla(subjectRequest.getSigla());
                    return subjectRepository.save(subject);
                })
        );
    }

    public boolean deleteSubject(Long id){
        return subjectRepository.findById(id)
                .map(subject -> {
                    subjectRepository.delete(subject);
                    return true;
                }).orElse(false);
    }


}