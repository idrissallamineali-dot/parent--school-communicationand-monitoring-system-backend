package com.auca.parent__school_communicationand_monitoring_system.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auca.parent__school_communicationand_monitoring_system.domain.MeetingStatus;
import com.auca.parent__school_communicationand_monitoring_system.domain.ParentMeeting;
import com.auca.parent__school_communicationand_monitoring_system.domain.Teacher;

public interface ParentMeetingRepository extends JpaRepository<ParentMeeting, UUID> {

    List<ParentMeeting> findByStatusOrderByMeetingDateAsc(MeetingStatus status);

    List<ParentMeeting> findByMeetingDateBetweenOrderByMeetingDateAsc(LocalDate start, LocalDate end);

    boolean existsByTeacherAndMeetingDateAndStatusNot(Teacher teacher, LocalDate meetingDate, MeetingStatus status);

}
