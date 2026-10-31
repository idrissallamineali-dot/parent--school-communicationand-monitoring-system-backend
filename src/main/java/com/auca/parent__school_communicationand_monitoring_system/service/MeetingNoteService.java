package com.auca.parent__school_communicationand_monitoring_system.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.auca.parent__school_communicationand_monitoring_system.domain.MeetingNote;
import com.auca.parent__school_communicationand_monitoring_system.domain.MeetingStatus;
import com.auca.parent__school_communicationand_monitoring_system.domain.ParentMeeting;
import com.auca.parent__school_communicationand_monitoring_system.repository.MeetingNoteRepository;
import com.auca.parent__school_communicationand_monitoring_system.repository.ParentMeetingRepository;

@Service
public class MeetingNoteService {

    private final MeetingNoteRepository meetingNoteRepository;
    private final ParentMeetingRepository parentMeetingRepository;

    public MeetingNoteService(
            MeetingNoteRepository meetingNoteRepository,
            ParentMeetingRepository parentMeetingRepository) {
        this.meetingNoteRepository = meetingNoteRepository;
        this.parentMeetingRepository = parentMeetingRepository;
    }

    public String saveMeetingNote(UUID parentMeetingId, MeetingNote note) {
        Optional<ParentMeeting> meetingOpt = parentMeetingRepository.findById(parentMeetingId);
        if (meetingOpt.isEmpty()) {
            return "The parent meeting with that id does not exist";
        }

        ParentMeeting meeting = meetingOpt.get();
        if (meeting.getStatus() != MeetingStatus.HELD) {
            return "Meeting notes are only for HELD parent meetings";
        }

        note.setWrittenDate(LocalDate.now());
        note.setParentMeeting(meeting);
        meetingNoteRepository.save(note);
        return "Meeting note saved successfully";
    }

    public List<MeetingNote> getNotesByStudent(String regNumber) {
        return meetingNoteRepository.findNotesByStudentRegNumber(regNumber);
    }

    public String deleteMeetingNote(UUID id) {
        if (!meetingNoteRepository.existsById(id)) {
            return "The meeting note with that id does not exist";
        }
        meetingNoteRepository.deleteById(id);
        return "Meeting note deleted successfully";
    }
}
