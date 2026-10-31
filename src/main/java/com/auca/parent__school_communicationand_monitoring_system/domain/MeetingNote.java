package com.auca.parent__school_communicationand_monitoring_system.domain;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "meeting_note")
public class MeetingNote {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String topic;
    private String summary;
    private int followUpDays;
    private LocalDate writtenDate;

    @ManyToOne
    @JoinColumn(name = "parent_meeting_id")
    private ParentMeeting parentMeeting;

    public MeetingNote() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getFollowUpDays() {
        return followUpDays;
    }

    public void setFollowUpDays(int followUpDays) {
        this.followUpDays = followUpDays;
    }

    public LocalDate getWrittenDate() {
        return writtenDate;
    }

    public void setWrittenDate(LocalDate writtenDate) {
        this.writtenDate = writtenDate;
    }

    public ParentMeeting getParentMeeting() {
        return parentMeeting;
    }

    public void setParentMeeting(ParentMeeting parentMeeting) {
        this.parentMeeting = parentMeeting;
    }
}
