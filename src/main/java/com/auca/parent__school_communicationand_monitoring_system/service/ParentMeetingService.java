package com.auca.parent__school_communicationand_monitoring_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.auca.parent__school_communicationand_monitoring_system.domain.ParentMeeting;
import com.auca.parent__school_communicationand_monitoring_system.repository.ParentMeetingRepository;

@Service
public class ParentMeetingService {

    private final ParentMeetingRepository parentMeetingRepository;

    public ParentMeetingService(ParentMeetingRepository parentMeetingRepository) {
        this.parentMeetingRepository = parentMeetingRepository;
    }

    public String saveParentMeeting(ParentMeeting parentMeeting) {
        parentMeetingRepository.save(parentMeeting);
        return "Parent meeting saved successfully";
    }

    public List<ParentMeeting> getAllParentMeetings() {
        return parentMeetingRepository.findAll();
    }

    public Optional<ParentMeeting> getParentMeetingById(UUID id) {
        return parentMeetingRepository.findById(id);
    }

    public String updateParentMeeting(UUID id, ParentMeeting data) {
        Optional<ParentMeeting> existing = parentMeetingRepository.findById(id);
        if (existing.isEmpty()) {
            return "The parent meeting with that id does not exist";
        }
        ParentMeeting meeting = existing.get();
        meeting.setMeetingDate(data.getMeetingDate());
        meeting.setStatus(data.getStatus());
        meeting.setReason(data.getReason());
        if (data.getStudent() != null) {
            meeting.setStudent(data.getStudent());
        }
        if (data.getTeacher() != null) {
            meeting.setTeacher(data.getTeacher());
        }
        parentMeetingRepository.save(meeting);
        return "Parent meeting updated successfully";
    }

    public String deleteParentMeeting(UUID id) {
        if (!parentMeetingRepository.existsById(id)) {
            return "The parent meeting with that id does not exist";
        }
        parentMeetingRepository.deleteById(id);
        return "Parent meeting deleted successfully";
    }
}
