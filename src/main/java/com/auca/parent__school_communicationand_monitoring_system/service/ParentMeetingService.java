package com.auca.parent__school_communicationand_monitoring_system.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.auca.parent__school_communicationand_monitoring_system.domain.MeetingStatus;
import com.auca.parent__school_communicationand_monitoring_system.domain.ParentMeeting;
import com.auca.parent__school_communicationand_monitoring_system.repository.MeetingNoteRepository;
import com.auca.parent__school_communicationand_monitoring_system.repository.ParentMeetingRepository;

@Service
public class ParentMeetingService {

    private final ParentMeetingRepository parentMeetingRepository;
    private final MeetingNoteRepository meetingNoteRepository;

    public ParentMeetingService(
            ParentMeetingRepository parentMeetingRepository,
            MeetingNoteRepository meetingNoteRepository) {
        this.parentMeetingRepository = parentMeetingRepository;
        this.meetingNoteRepository = meetingNoteRepository;
    }

    public String saveParentMeeting(ParentMeeting parentMeeting) {
        if (parentMeeting.getMeetingDate() != null && parentMeeting.getMeetingDate().isBefore(LocalDate.now())) {
            return "Parent meeting date cannot be in the past";
        }

        if (parentMeeting.getTeacher() != null && parentMeeting.getMeetingDate() != null) {
            if (parentMeetingRepository.existsByTeacherAndMeetingDateAndStatusNot(
                    parentMeeting.getTeacher(), parentMeeting.getMeetingDate(), MeetingStatus.DECLINED)) {
                return "Teacher already has a parent meeting on that date";
            }
        }

        parentMeeting.setStatus(MeetingStatus.REQUESTED);
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
        Optional<ParentMeeting> meetingOpt = parentMeetingRepository.findById(id);
        if (meetingOpt.isEmpty()) {
            return "The parent meeting with that id does not exist";
        }
        ParentMeeting meeting = meetingOpt.get();
        if (meetingNoteRepository.existsByParentMeeting(meeting)) {
            return "That parent meeting has meeting notes, delete them first";
        }
        parentMeetingRepository.delete(meeting);
        return "Parent meeting deleted successfully";
    }

    public List<ParentMeeting> getMeetingsByStatus(MeetingStatus status) {
        return parentMeetingRepository.findByStatusOrderByMeetingDateAsc(status);
    }

    public List<ParentMeeting> getMeetingsBetween(LocalDate start, LocalDate end) {
        return parentMeetingRepository.findByMeetingDateBetweenOrderByMeetingDateAsc(start, end);
    }

    public String updateStatus(UUID id, MeetingStatus newStatus) {
        Optional<ParentMeeting> existing = parentMeetingRepository.findById(id);
        if (existing.isEmpty()) {
            return "The parent meeting with that id does not exist";
        }

        ParentMeeting meeting = existing.get();
        MeetingStatus current = meeting.getStatus();

        boolean allowed = false;
        if (current == MeetingStatus.REQUESTED && (newStatus == MeetingStatus.ACCEPTED || newStatus == MeetingStatus.DECLINED)) {
            allowed = true;
        } else if (current == MeetingStatus.ACCEPTED && (newStatus == MeetingStatus.HELD || newStatus == MeetingStatus.DECLINED)) {
            allowed = true;
        }

        if (!allowed) {
            return "Cannot change status from " + current + " to " + newStatus;
        }

        meeting.setStatus(newStatus);
        parentMeetingRepository.save(meeting);
        return "Parent meeting status updated successfully";
    }
}
