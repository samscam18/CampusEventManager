package com.example.campus_events.controller;


import com.example.campus_events.model.Registration;
import com.example.campus_events.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/registrations")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationRepository registrationRepository;

    @PostMapping
    public ResponseEntity<?> registerStudent(@RequestBody Registration registration) {
        boolean exists = registrationRepository.existsByStudentIdAndEventId(
                registration.getStudent().getId(), registration.getEvent().getId()
        );
        if (exists) return ResponseEntity.badRequest().body("Student already registered for this event");
        return ResponseEntity.ok(registrationRepository.save(registration));
    }


    @GetMapping
    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Registration> getRegistration(@PathVariable UUID id) {
        return registrationRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
