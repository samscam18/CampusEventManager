package com.example.campus_events.service;

import com.example.campus_events.dto.EventPopularityDto;
import com.example.campus_events.dto.TopStudentDto;
import com.example.campus_events.repository.AttendanceRepository;
import com.example.campus_events.repository.FeedbackRepository;
import com.example.campus_events.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final RegistrationRepository registrationRepository;
    private final AttendanceRepository attendanceRepository;
    private final FeedbackRepository feedbackRepository;

    // 1) Event report
    public String getEventReport(UUID eventId) {
        long totalRegs = registrationRepository.countByEventId(eventId);
        long attended = attendanceRepository.countAttendanceByEventId(eventId);
        double attendancePercent = totalRegs == 0 ? 0.0 : (attended * 100.0) / totalRegs;
        Double avgRating = feedbackRepository.findAverageRatingByEventId(eventId);
        long feedbackCount = feedbackRepository.countFeedbackByEventId(eventId);

        return "Event Report => Registrations: " + totalRegs +
                ", Attended: " + attended +
                ", Attendance %: " + attendancePercent +
                ", Feedbacks: " + feedbackCount +
                ", Avg Rating: " + (avgRating == null ? "N/A" : avgRating);
    }

    // 2) Popular events
    public List<EventPopularityDto> getPopularEvents(UUID collegeId) {
        List<Object[]> rows = registrationRepository.findEventPopularity(collegeId, null);
        List<EventPopularityDto> result = new ArrayList<>();
        for (Object[] row : rows) {
            result.add(new EventPopularityDto(
                    (UUID) row[0],
                    (String) row[1],
                    (Long) row[2]
            ));
        }
        return result;
    }

    // 3) Top students
    public List<TopStudentDto> getTopStudents(UUID collegeId) {
        List<Object[]> rows = registrationRepository.findTopStudentsByCollege(collegeId);
        List<TopStudentDto> result = new ArrayList<>();
        for (Object[] row : rows) {
            result.add(new TopStudentDto(
                    (UUID) row[0],
                    (String) row[1],
                    (Long) row[2]
            ));
        }
        return result;
    }
}
