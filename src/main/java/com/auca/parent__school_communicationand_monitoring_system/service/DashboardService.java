package com.auca.parent__school_communicationand_monitoring_system.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.auca.parent__school_communicationand_monitoring_system.domain.MeetingStatus;
import com.auca.parent__school_communicationand_monitoring_system.repository.ClassroomRepository;
import com.auca.parent__school_communicationand_monitoring_system.repository.MeetingNoteRepository;
import com.auca.parent__school_communicationand_monitoring_system.repository.ParentMeetingRepository;
import com.auca.parent__school_communicationand_monitoring_system.repository.StudentRepository;
import com.auca.parent__school_communicationand_monitoring_system.repository.TeacherRepository;

@Service
public class DashboardService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final ClassroomRepository classroomRepository;
    private final ParentMeetingRepository parentMeetingRepository;
    private final MeetingNoteRepository meetingNoteRepository;

    public DashboardService(
            StudentRepository studentRepository,
            TeacherRepository teacherRepository,
            ClassroomRepository classroomRepository,
            ParentMeetingRepository parentMeetingRepository,
            MeetingNoteRepository meetingNoteRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.classroomRepository = classroomRepository;
        this.parentMeetingRepository = parentMeetingRepository;
        this.meetingNoteRepository = meetingNoteRepository;
    }

    public Map<String, Long> getSummary() {
        Map<String, Long> summary = new LinkedHashMap<>();

        summary.put("students", studentRepository.count());
        summary.put("teachers", teacherRepository.count());
        summary.put("freeClassrooms", (long) classroomRepository.findFreeClassrooms().size());
        summary.put("meetingsToday", parentMeetingRepository.countByMeetingDate(LocalDate.now()));
        summary.put("requested", parentMeetingRepository.countByStatus(MeetingStatus.REQUESTED));
        summary.put("meetingNotes", meetingNoteRepository.count());

        return summary;
    }
}
