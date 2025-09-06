package com.example.campus_events.controller;


import com.example.campus_events.model.Attendance;
import com.example.campus_events.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceRepository attendanceRepository;

    @PostMapping
    public ResponseEntity<?> markAttendance(@RequestBody Attendance attendance) {
        if(attendanceRepository.findByRegistrationId(attendance.getRegistration().getId()).isPresent()){
            return ResponseEntity.badRequest().body("Attendance already marked for this registration");
        }
        return ResponseEntity.ok(attendanceRepository.save(attendance));
    }

    @GetMapping
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendance> getAttendance(@PathVariable UUID id) {
        return attendanceRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }


}
