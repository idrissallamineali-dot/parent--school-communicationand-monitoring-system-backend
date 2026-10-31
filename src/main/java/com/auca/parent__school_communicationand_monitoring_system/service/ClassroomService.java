package com.auca.parent__school_communicationand_monitoring_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.auca.parent__school_communicationand_monitoring_system.domain.Classroom;
import com.auca.parent__school_communicationand_monitoring_system.repository.ClassroomRepository;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    public String saveClassroom(Classroom classroom) {
        classroomRepository.save(classroom);
        return "Classroom saved successfully";
    }

    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    public Optional<Classroom> getClassroomById(UUID id) {
        return classroomRepository.findById(id);
    }

    public String updateClassroom(UUID id, Classroom data) {
        Optional<Classroom> existing = classroomRepository.findById(id);
        if (existing.isEmpty()) {
            return "The classroom with that id does not exist";
        }
        Classroom classroom = existing.get();
        classroom.setName(data.getName());
        classroom.setRoomNumber(data.getRoomNumber());
        classroomRepository.save(classroom);
        return "Classroom updated successfully";
    }

    public String deleteClassroom(UUID id) {
        if (!classroomRepository.existsById(id)) {
            return "The classroom with that id does not exist";
        }
        classroomRepository.deleteById(id);
        return "Classroom deleted successfully";
    }
}
