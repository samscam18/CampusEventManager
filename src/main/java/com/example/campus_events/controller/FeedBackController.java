package com.example.campus_events.controller;


import com.example.campus_events.model.Feedback;
import com.example.campus_events.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/feedback")
@RequiredArgsConstructor
public class FeedBackController {

    private final FeedbackRepository feedbackRepository;

    @PostMapping
    public ResponseEntity<?> submitFeedback(@RequestBody Feedback feedback) {
        if(feedbackRepository.findByRegistrationId(feedback.getRegistration().getId()).isPresent()){
            return ResponseEntity.badRequest().body("Feedback already submitted for this registration");
        }
        return ResponseEntity.ok(feedbackRepository.save(feedback));
    }


    @GetMapping
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedback(@PathVariable UUID id) {
        return feedbackRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
