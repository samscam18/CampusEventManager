package com.example.campus_events.repository;

import com.example.campus_events.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, UUID> {

    Optional<Feedback> findByRegistrationId(UUID registrationId);

    @Query("""
       SELECT AVG(f.rating)
       FROM Feedback f
       JOIN f.registration r
       WHERE r.event.id = :eventId
    """)
    Double findAverageRatingByEventId(@Param("eventId") UUID eventId);

    @Query("""
       SELECT COUNT(f)
       FROM Feedback f
       JOIN f.registration r
       WHERE r.event.id = :eventId
    """)
    long countFeedbackByEventId(@Param("eventId") UUID eventId);
}
