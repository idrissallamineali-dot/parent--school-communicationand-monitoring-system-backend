package com.auca.parent__school_communicationand_monitoring_system.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auca.parent__school_communicationand_monitoring_system.domain.MeetingNote;
import com.auca.parent__school_communicationand_monitoring_system.service.MeetingNoteService;

@RestController
@RequestMapping("/api/meeting-notes")
public class MeetingNoteController {

    @Autowired
    private MeetingNoteService meetingNoteService;

    @PostMapping("/save")
    public ResponseEntity<String> save(
            @RequestParam UUID parentMeetingId,
            @RequestBody MeetingNote meetingNote) {
        String message = meetingNoteService.saveMeetingNote(parentMeetingId, meetingNote);
        if (message.equals("The parent meeting with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        if (message.equals("Meeting notes are only for HELD parent meetings")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/student/{regNumber}")
    public ResponseEntity<List<MeetingNote>> getByStudent(@PathVariable String regNumber) {
        List<MeetingNote> notes = meetingNoteService.getNotesByStudent(regNumber);
        return ResponseEntity.ok(notes);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = meetingNoteService.deleteMeetingNote(id);
        if (message.equals("The meeting note with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
