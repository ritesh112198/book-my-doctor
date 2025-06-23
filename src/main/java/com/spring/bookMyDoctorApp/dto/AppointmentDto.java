package com.spring.bookMyDoctorApp.dto;

import com.spring.bookMyDoctorApp.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {
    private String id;
    private LocalDateTime appointmentTime;
    private String patientId;
    private String doctorId;
}
