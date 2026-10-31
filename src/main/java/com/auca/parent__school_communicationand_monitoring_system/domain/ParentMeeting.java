package com.auca.parent__school_communicationand_monitoring_system.domain;

import java.time.LocalDate;
import java.util.UUID;

import com.auca.parent__school_communicationand_monitoring_system.domain.MeetingStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "parent_meeting")
public class ParentMeeting {
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID   id;

    LocalDate meetingDate;

    @Enumerated(EnumType.STRING)
    private MeetingStatus status;

    String reason;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public LocalDate getMeetingDate() {
        return meetingDate;
    }
    public void setMeetingDate(LocalDate meetingDate) {
        this.meetingDate = meetingDate;
    }
    public MeetingStatus getStatus() {
        return status;
    }

    public void setStatus(MeetingStatus status) {
        this.status = status;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }



}
