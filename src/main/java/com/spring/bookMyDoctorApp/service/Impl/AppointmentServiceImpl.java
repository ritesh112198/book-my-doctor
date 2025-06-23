package com.spring.bookMyDoctorApp.service.Impl;

import com.spring.bookMyDoctorApp.dto.AppointmentDto;
import com.spring.bookMyDoctorApp.entity.Appointment;
import com.spring.bookMyDoctorApp.entity.User;
import com.spring.bookMyDoctorApp.exceptionHandling.AppointmentException;
import com.spring.bookMyDoctorApp.repository.AppointmentRepository;
import com.spring.bookMyDoctorApp.repository.UserRepository;
import com.spring.bookMyDoctorApp.service.AppointmentService;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AppointmentDto bookAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentTime(appointmentDto.getAppointmentTime());

        User patient = userRepository.findById(appointmentDto.getPatientId())
                .orElseThrow(() -> new AppointmentException("Patient Not Found"));

        User doctor = userRepository.findById(appointmentDto.getDoctorId())
                .orElseThrow(() -> new AppointmentException("Doctor Not Found"));

        appointment.setPatientId(patient);
        appointment.setDoctorId(doctor);

        Appointment savedAppointment = appointmentRepository.save(appointment);

        return modelMapper.map(savedAppointment, AppointmentDto.class);
    }
    @Override
    public List<AppointmentDto> getAppointmentsByDoctorId(String doctorId) {
        List<Appointment> appointments = appointmentRepository.findByDoctorId(doctorId);

        return appointments.stream()
                .map(a-> {
                    AppointmentDto dto = new AppointmentDto();
                    dto.setAppointmentTime(a.getAppointmentTime());
                    dto.setDoctorId(a.getDoctorId().get_id());
                    dto.setPatientId(a.getPatientId().get_id());
                    return dto;
                }).collect(Collectors.toList());
    }
}