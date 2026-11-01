package com.auca.parent__school_communicationand_monitoring_system.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.auca.parent__school_communicationand_monitoring_system.domain.MeetingStatus;
import com.auca.parent__school_communicationand_monitoring_system.domain.ParentMeeting;
import com.auca.parent__school_communicationand_monitoring_system.domain.Teacher;

public interface ParentMeetingRepository extends JpaRepository<ParentMeeting, UUID> {

    List<ParentMeeting> findByStatusOrderByMeetingDateAsc(MeetingStatus status);

    List<ParentMeeting> findByMeetingDateBetweenOrderByMeetingDateAsc(LocalDate start, LocalDate end);

    boolean existsByTeacherAndMeetingDateAndStatusNot(Teacher teacher, LocalDate meetingDate, MeetingStatus status);

    @Query("SELECT m.status, COUNT(m) FROM ParentMeeting m GROUP BY m.status")
    List<Object[]> countParentMeetingsByStatus();

    @Modifying
    @Query("UPDATE ParentMeeting m SET m.status = :declined WHERE m.teacher.id = :teacherId AND m.meetingDate = :date AND m.status != :held")
    int cancelMeetingsForTeacherOnDate(
            @Param("teacherId") UUID teacherId,
            @Param("date") LocalDate date,
            @Param("declined") MeetingStatus declined,
            @Param("held") MeetingStatus held
    );

    long countByMeetingDate(LocalDate date);

    long countByStatus(MeetingStatus status);

}
