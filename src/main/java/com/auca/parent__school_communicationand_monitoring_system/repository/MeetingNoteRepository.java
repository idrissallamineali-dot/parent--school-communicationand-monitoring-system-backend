package com.auca.parent__school_communicationand_monitoring_system.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.auca.parent__school_communicationand_monitoring_system.domain.MeetingNote;
import com.auca.parent__school_communicationand_monitoring_system.domain.ParentMeeting;

public interface MeetingNoteRepository extends JpaRepository<MeetingNote, UUID> {

    @Query("SELECT n FROM MeetingNote n WHERE n.parentMeeting.student.regNumber = :regNumber ORDER BY n.writtenDate DESC")
    List<MeetingNote> findNotesByStudentRegNumber(@Param("regNumber") String regNumber);

    boolean existsByParentMeeting(ParentMeeting parentMeeting);

}
