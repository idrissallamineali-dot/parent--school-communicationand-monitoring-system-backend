package com.auca.parent__school_communicationand_monitoring_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.auca.parent__school_communicationand_monitoring_system.domain.Student;
import com.auca.parent__school_communicationand_monitoring_system.repository.StudentRepository;
import com.auca.parent__school_communicationand_monitoring_system.repository.TeacherRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public StudentService(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    public String saveStudent(Student student) {
        studentRepository.save(student);
        return "Student saved successfully";
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(UUID id) {
        return studentRepository.findById(id);
    }

    public String updateStudent(UUID id, Student data) {
        Optional<Student> existing = studentRepository.findById(id);
        if (existing.isEmpty()) {
            return "The student with that id does not exist";
        }
        Student student = existing.get();
        student.setFirstName(data.getFirstName());
        student.setLastName(data.getLastName());
        student.setRegNumber(data.getRegNumber());
        student.setParentName(data.getParentName());
        student.setParentPhone(data.getParentPhone());
        studentRepository.save(student);
        return "Student updated successfully";
    }

    public String deleteStudent(UUID id) {
        if (!studentRepository.existsById(id)) {
            return "The student with that id does not exist";
        }
        studentRepository.deleteById(id);
        return "Student deleted successfully";
    }

    public List<Student> getStudentsByLastName(String lastName) {
        return studentRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }

    public Optional<List<Student>> getStudentsOfTeacher(UUID teacherId) {
        if (!teacherRepository.existsById(teacherId)) {
            return Optional.empty();
        }
        return Optional.of(studentRepository.findStudentsByTeacherId(teacherId));
    }
}
