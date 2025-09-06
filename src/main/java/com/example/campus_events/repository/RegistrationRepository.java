package com.example.campus_events.repository;

import com.example.campus_events.model.Event;
import com.example.campus_events.model.EventType;
import com.example.campus_events.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, UUID> {

    boolean existsByStudentIdAndEventId(UUID studentId, UUID eventId);

    List<Registration> findByEventId(UUID eventId);
    List<Registration> findByStudentId(UUID studentId);
    long countByEventId(UUID eventId);

    @Query("""
        SELECT r.event.id, r.event.title, COUNT(r)
        FROM Registration r
        WHERE (:collegeId IS NULL OR r.event.college.id = :collegeId)
          AND (:type IS NULL OR r.event.type = :type)
        GROUP BY r.event.id, r.event.title
        ORDER BY COUNT(r) DESC
    """)
    List<Object[]> findEventPopularity(
            @Param("collegeId") UUID collegeId,
            @Param("type") EventType type
    );

    @Query("""
        SELECT r.student.id, r.student.name, COUNT(a)
        FROM Registration r
        LEFT JOIN Attendance a ON a.registration = r
        WHERE (:collegeId IS NULL OR r.student.college.id = :collegeId)
        GROUP BY r.student.id, r.student.name
        ORDER BY COUNT(a) DESC, r.student.name ASC
    """)
    List<Object[]> findTopStudentsByCollege(@Param("collegeId") UUID collegeId);

    @Query("""
        SELECT r.event
        FROM Registration r
        WHERE r.id = :registrationId
    """)
    Optional<Event> findEventByRegistrationId(@Param("registrationId") UUID registrationId);
}
