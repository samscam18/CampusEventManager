package com.example.campus_events.repository;

import com.example.campus_events.model.Event;
import com.example.campus_events.model.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

    // Find events by college
    List<Event> findByCollegeId(UUID collegeId);

    // Find events by type
    List<Event> findByType(EventType type);

    // Optional: Find events by college and type
    List<Event> findByCollegeIdAndType(UUID collegeId, EventType type);
}
