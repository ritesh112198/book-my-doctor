package com.spring.bookMyDoctorApp.controller;


import com.spring.bookMyDoctorApp.dto.AppointmentDto;
import com.spring.bookMyDoctorApp.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/book")
    public AppointmentDto bookAppointments(@RequestBody AppointmentDto appointmentDto){
        return appointmentService.bookAppointment(appointmentDto);

    }

}
