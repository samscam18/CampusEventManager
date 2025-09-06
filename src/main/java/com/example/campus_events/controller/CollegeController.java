package com.example.campus_events.controller;

import com.example.campus_events.model.College;
import com.example.campus_events.repository.CollegeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/colleges")
@RequiredArgsConstructor
public class CollegeController {

    private final CollegeRepository collegeRepository;

    // Create a college
    @PostMapping
    public ResponseEntity<College> createCollege(@RequestBody College college) {
        return ResponseEntity.ok(collegeRepository.save(college));
    }

    // Get all colleges
    @GetMapping
    public List<College> getAllColleges() {
        return collegeRepository.findAll();
    }

    // Get a single college by ID
    @GetMapping("/{id}")
    public ResponseEntity<College> getCollege(@PathVariable UUID id) {
        return collegeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
