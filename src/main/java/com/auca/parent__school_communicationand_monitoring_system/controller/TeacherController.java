package com.auca.parent__school_communicationand_monitoring_system.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auca.parent__school_communicationand_monitoring_system.domain.Teacher;
import com.auca.parent__school_communicationand_monitoring_system.service.TeacherService;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Teacher teacher) {
        String message = teacherService.saveTeacher(teacher);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Teacher>> getAll() {
        return new ResponseEntity<>(teacherService.getAllTeachers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Teacher> teacher = teacherService.getTeacherById(id);
        if (teacher.isEmpty()) {
            return new ResponseEntity<>("The teacher with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(teacher.get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Teacher teacher) {
        String message = teacherService.updateTeacher(id, teacher);
        if (message.equals("The teacher with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = teacherService.deleteTeacher(id);
        if (message.equals("The teacher with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/by-subject")
    public ResponseEntity<List<Teacher>> getBySubject(@RequestParam String name) {
        List<Teacher> teachers = teacherService.getTeachersBySubject(name);
        return ResponseEntity.ok(teachers);
    }

    @PutMapping("/{teacherId}/subjects/{subjectId}")
    public ResponseEntity<String> addSubject(
            @PathVariable UUID teacherId,
            @PathVariable UUID subjectId) {
        String message = teacherService.addSubjectToTeacher(teacherId, subjectId);
        if (message.equals("The teacher with that id does not exist") || message.equals("That subject does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        if (message.equals("The teacher already has that subject")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PatchMapping("/{teacherId}/classroom")
    public ResponseEntity<String> moveClassroom(
            @PathVariable UUID teacherId,
            @RequestParam int roomNumber) {
        String message = teacherService.moveTeacherToClassroom(teacherId, roomNumber);
        if (message.equals("The teacher with that id does not exist") || message.equals("The classroom with that roomNumber does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        if (message.equals("That classroom is taken by another teacher")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
