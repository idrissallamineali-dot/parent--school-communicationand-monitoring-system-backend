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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auca.parent__school_communicationand_monitoring_system.domain.Student;
import com.auca.parent__school_communicationand_monitoring_system.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Student student) {
        String message = studentService.saveStudent(student);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAll() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isEmpty()) {
            return new ResponseEntity<>("The student with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student.get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Student student) {
        String message = studentService.updateStudent(id, student);
        if (message.equals("The student with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = studentService.deleteStudent(id);
        if (message.equals("The student with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/by-last-name")
    public ResponseEntity<List<Student>> getByLastName(@RequestParam String lastName) {
        List<Student> students = studentService.getStudentsByLastName(lastName);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/of-teacher/{teacherId}")
    public ResponseEntity<?> getStudentsOfTeacher(@PathVariable UUID teacherId) {
        Optional<List<Student>> students = studentService.getStudentsOfTeacher(teacherId);
        if (students.isEmpty()) {
            return new ResponseEntity<>("The teacher with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(students.get());
    }
}
