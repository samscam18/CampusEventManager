package com.example.campus_events.repository;

import com.example.campus_events.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, UUID> {

    Optional<Attendance> findByRegistrationId(UUID registrationId);

    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.registration.event.id = :eventId")
    long countAttendanceByEventId(@Param("eventId") UUID eventId);
}
