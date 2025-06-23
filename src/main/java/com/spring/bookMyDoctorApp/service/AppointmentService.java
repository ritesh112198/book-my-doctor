package com.spring.bookMyDoctorApp.service;

import com.spring.bookMyDoctorApp.dto.AppointmentDto;

import java.util.List;

public interface AppointmentService {
    AppointmentDto bookAppointment(AppointmentDto appointmentDto);

   // List<AppointmentDto> bookAppointment(String userId);

    List<AppointmentDto> getAppointmentsByDoctorId(String doctorId);
}
