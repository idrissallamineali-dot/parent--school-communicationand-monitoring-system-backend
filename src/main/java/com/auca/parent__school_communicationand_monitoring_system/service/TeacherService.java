package com.auca.parent__school_communicationand_monitoring_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.auca.parent__school_communicationand_monitoring_system.domain.Classroom;
import com.auca.parent__school_communicationand_monitoring_system.domain.Subject;
import com.auca.parent__school_communicationand_monitoring_system.domain.Teacher;
import com.auca.parent__school_communicationand_monitoring_system.repository.ClassroomRepository;
import com.auca.parent__school_communicationand_monitoring_system.repository.SubjectRepository;
import com.auca.parent__school_communicationand_monitoring_system.repository.TeacherRepository;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final ClassroomRepository classroomRepository;

    public TeacherService(
            TeacherRepository teacherRepository,
            SubjectRepository subjectRepository,
            ClassroomRepository classroomRepository) {
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
        this.classroomRepository = classroomRepository;
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

    public List<Teacher> getTeachersBySubject(String name) {
        return teacherRepository.findTeachersBySubjectName(name);
    }

    public String addSubjectToTeacher(UUID teacherId, UUID subjectId) {
        Optional<Teacher> teacherOpt = teacherRepository.findById(teacherId);
        if (teacherOpt.isEmpty()) {
            return "The teacher with that id does not exist";
        }

        Optional<Subject> subjectOpt = subjectRepository.findById(subjectId);
        if (subjectOpt.isEmpty()) {
            return "That subject does not exist";
        }

        Teacher teacher = teacherOpt.get();
        Subject subject = subjectOpt.get();

        boolean alreadyHas = teacher.getSubjects().stream().anyMatch(s -> s.getId().equals(subjectId));
        if (alreadyHas) {
            return "The teacher already has that subject";
        }

        teacher.getSubjects().add(subject);
        teacherRepository.save(teacher);
        return "Subject added to the teacher";
    }

    public String moveTeacherToClassroom(UUID teacherId, int roomNumber) {
        Optional<Teacher> teacherOpt = teacherRepository.findById(teacherId);
        if (teacherOpt.isEmpty()) {
            return "The teacher with that id does not exist";
        }

        Optional<Classroom> classroomOpt = classroomRepository.findByRoomNumber(roomNumber);
        if (classroomOpt.isEmpty()) {
            return "The classroom with that roomNumber does not exist";
        }

        Classroom classroom = classroomOpt.get();
        Teacher teacher = teacherOpt.get();

        if (teacherRepository.existsByClassroom(classroom)) {
            return "That classroom is taken by another teacher";
        }

        teacher.setClassroom(classroom);
        teacherRepository.save(teacher);
        return "Classroom changed successfully";
    }
}
