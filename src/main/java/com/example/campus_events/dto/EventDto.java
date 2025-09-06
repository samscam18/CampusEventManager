package com.example.campus_events.dto;

import com.example.campus_events.model.EventType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class EventDto {
    @NotNull
    private UUID collegeId;
    @NotBlank
    private String title;
    @NotNull
    private EventType type;
    @NotNull
    private Instant startTime;
    @NotNull
    private Instant endTime;
    private String location;
    private String description;
}

