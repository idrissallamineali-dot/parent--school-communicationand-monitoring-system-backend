package com.auca.parent__school_communicationand_monitoring_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.auca.parent__school_communicationand_monitoring_system.domain.Teacher;
import com.auca.parent__school_communicationand_monitoring_system.repository.TeacherRepository;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public String saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
        return "Teacher saved successfully";
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> getTeacherById(UUID id) {
        return teacherRepository.findById(id);
    }

    public String updateTeacher(UUID id, Teacher data) {
        Optional<Teacher> existing = teacherRepository.findById(id);
        if (existing.isEmpty()) {
            return "The teacher with that id does not exist";
        }
        Teacher teacher = existing.get();
        teacher.setFirstName(data.getFirstName());
        teacher.setLastName(data.getLastName());
        teacher.setEmail(data.getEmail());
        if (data.getClassroom() != null) {
            teacher.setClassroom(data.getClassroom());
        }
        if (data.getSubjects() != null) {
            teacher.setSubjects(data.getSubjects());
        }
        teacherRepository.save(teacher);
        return "Teacher updated successfully";
    }

    public String deleteTeacher(UUID id) {
        if (!teacherRepository.existsById(id)) {
            return "The teacher with that id does not exist";
        }
        teacherRepository.deleteById(id);
        return "Teacher deleted successfully";
    }
}
