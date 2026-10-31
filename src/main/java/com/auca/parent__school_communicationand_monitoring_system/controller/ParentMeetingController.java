package com.auca.parent__school_communicationand_monitoring_system.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auca.parent__school_communicationand_monitoring_system.domain.MeetingStatus;
import com.auca.parent__school_communicationand_monitoring_system.domain.ParentMeeting;
import com.auca.parent__school_communicationand_monitoring_system.service.ParentMeetingService;

@RestController
@RequestMapping("/api/parent-meetings")
public class ParentMeetingController {

    @Autowired
    private ParentMeetingService parentMeetingService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody ParentMeeting parentMeeting) {
        String message = parentMeetingService.saveParentMeeting(parentMeeting);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ParentMeeting>> getAll() {
        return new ResponseEntity<>(parentMeetingService.getAllParentMeetings(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<ParentMeeting> meeting = parentMeetingService.getParentMeetingById(id);
        if (meeting.isEmpty()) {
            return new ResponseEntity<>("The parent meeting with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(meeting.get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody ParentMeeting parentMeeting) {
        String message = parentMeetingService.updateParentMeeting(id, parentMeeting);
        if (message.equals("The parent meeting with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = parentMeetingService.deleteParentMeeting(id);
        if (message.equals("The parent meeting with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/by-status")
    public ResponseEntity<List<ParentMeeting>> getByStatus(@RequestParam MeetingStatus status) {
        List<ParentMeeting> meetings = parentMeetingService.getMeetingsByStatus(status);
        return ResponseEntity.ok(meetings);
    }

    @GetMapping("/between")
    public ResponseEntity<List<ParentMeeting>> getBetween(
            @RequestParam String start,
            @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        List<ParentMeeting> meetings = parentMeetingService.getMeetingsBetween(startDate, endDate);
        return ResponseEntity.ok(meetings);
    }
}
