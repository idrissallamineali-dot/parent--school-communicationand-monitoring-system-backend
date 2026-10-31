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

import com.auca.parent__school_communicationand_monitoring_system.domain.Classroom;
import com.auca.parent__school_communicationand_monitoring_system.service.ClassroomService;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Classroom classroom) {
        String message = classroomService.saveClassroom(classroom);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Classroom>> getAll() {
        return new ResponseEntity<>(classroomService.getAllClassrooms(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Classroom> classroom = classroomService.getClassroomById(id);
        if (classroom.isEmpty()) {
            return new ResponseEntity<>("The classroom with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(classroom.get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Classroom classroom) {
        String message = classroomService.updateClassroom(id, classroom);
        if (message.equals("The classroom with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = classroomService.deleteClassroom(id);
        if (message.equals("The classroom with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
