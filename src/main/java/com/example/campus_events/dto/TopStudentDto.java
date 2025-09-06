package com.example.campus_events.dto;

import java.util.UUID;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopStudentDto {
    private UUID studentId;
    private String name;
    private Long attendedEvents;
}

