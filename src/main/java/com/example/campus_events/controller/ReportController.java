package com.example.campus_events.controller;

import com.example.campus_events.dto.EventPopularityDto;
import com.example.campus_events.dto.TopStudentDto;
import com.example.campus_events.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/event/{eventId}")
    public ResponseEntity<String> getEventReport(@PathVariable UUID eventId) {
        return ResponseEntity.ok(reportService.getEventReport(eventId));
    }

    @GetMapping("/popular-events/{collegeId}")
    public ResponseEntity<List<EventPopularityDto>> getPopularEvents(@PathVariable UUID collegeId) {
        return ResponseEntity.ok(reportService.getPopularEvents(collegeId));
    }


    @GetMapping("/top-students/{collegeId}")
    public ResponseEntity<List<TopStudentDto>> getTopStudents(@PathVariable UUID collegeId) {
        return ResponseEntity.ok(reportService.getTopStudents(collegeId));
    }
}
