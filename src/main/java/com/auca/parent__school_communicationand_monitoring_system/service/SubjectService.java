package com.auca.parent__school_communicationand_monitoring_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.auca.parent__school_communicationand_monitoring_system.domain.Subject;
import com.auca.parent__school_communicationand_monitoring_system.repository.SubjectRepository;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public String saveSubject(Subject subject) {
        subjectRepository.save(subject);
        return "Subject saved successfully";
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Optional<Subject> getSubjectById(UUID id) {
        return subjectRepository.findById(id);
    }

    public String updateSubject(UUID id, Subject data) {
        Optional<Subject> existing = subjectRepository.findById(id);
        if (existing.isEmpty()) {
            return "The subject with that id does not exist";
        }
        Subject subject = existing.get();
        subject.setName(data.getName());
        subjectRepository.save(subject);
        return "Subject updated successfully";
    }

    public String deleteSubject(UUID id) {
        if (!subjectRepository.existsById(id)) {
            return "The subject with that id does not exist";
        }
        subjectRepository.deleteById(id);
        return "Subject deleted successfully";
    }
}
