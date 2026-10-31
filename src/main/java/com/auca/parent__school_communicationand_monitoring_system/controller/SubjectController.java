package com.auca.parent__school_communicationand_monitoring_system.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auca.parent__school_communicationand_monitoring_system.domain.Subject;
import com.auca.parent__school_communicationand_monitoring_system.service.SubjectService;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Subject subject) {
        String message = subjectService.saveSubject(subject);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Subject>> getAll() {
        return new ResponseEntity<>(subjectService.getAllSubjects(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Subject> subject = subjectService.getSubjectById(id);
        if (subject.isEmpty()) {
            return new ResponseEntity<>("The subject with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(subject.get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Subject subject) {
        String message = subjectService.updateSubject(id, subject);
        if (message.equals("The subject with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = subjectService.deleteSubject(id);
        if (message.equals("The subject with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/unused")
    public ResponseEntity<List<Subject>> getUnusedSubjects() {
        return ResponseEntity.ok(subjectService.getUnusedSubjects());
    }
}
