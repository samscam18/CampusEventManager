package com.example.campus_events.controller;


import com.example.campus_events.dto.EventDto;
import com.example.campus_events.model.College;
import com.example.campus_events.model.Event;
import com.example.campus_events.model.EventType;
import com.example.campus_events.repository.CollegeRepository;
import com.example.campus_events.repository.EventRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventRepository eventRepository;
    private final CollegeRepository collegeRepository;

    @PostMapping
    public ResponseEntity<Event> createEvent(@Valid @RequestBody EventDto dto) {
        College college = collegeRepository.findById(dto.getCollegeId())
                .orElseThrow(() -> new RuntimeException("College not found"));

        Event event = Event.builder()
                .college(college)
                .title(dto.getTitle())
                .type(dto.getType())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .location(dto.getLocation())
                .description(dto.getDescription())
                .build();

        return ResponseEntity.ok(eventRepository.save(event));
    }


    @GetMapping
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable UUID id) {
        return eventRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/filter")
    public List<Event> getEventsByCollegeAndType(@RequestParam(required = false) UUID collegeId,
                                                 @RequestParam(required = false) EventType type) {
        if (collegeId != null && type != null) return eventRepository.findByCollegeIdAndType(collegeId, type);
        if (collegeId != null) return eventRepository.findByCollegeId(collegeId);
        if (type != null) return eventRepository.findByType(type);
        return eventRepository.findAll();
    }

}
