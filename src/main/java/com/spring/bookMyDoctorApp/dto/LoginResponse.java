package com.spring.bookMyDoctorApp.dto;

import com.spring.bookMyDoctorApp.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Document(collection = "loginResponse")
public class LoginResponse {
//    @Id
//    private String id;
    private String message;
    private Role role;

    // For doctor
    private List<AppointmentDto> appointments;

    // For admin and patient
    private List<UserDto> doctors;

    // For admin only
    private List<LeaveRequestDto> leaveRequests;

    // Constructor for message + role only
    public LoginResponse(String message, Role role) {
        this.message = message;
        this.role = role;
    }
//
//    // Constructor for doctor login
//    public LoginResponse(String message, Role role, List<AppointmentDto> appointments) {
//        this.message = message;
//        this.role = role;
//        this.appointments = appointments;
//    }
//
//    // Constructor for admin login
//    public LoginResponse(String message, Role role, List<UserDto> doctors, List<LeaveRequestDto> leaveRequests) {
//        this.message = message;
//        this.role = role;
//        this.doctors = doctors;
//        this.leaveRequests = leaveRequests;
//    }
//
//    // Constructor for patient login (doctors list only)
//    public LoginResponse(String message, Role role, List<UserDto> doctors) {
//        this.message = message;
//        this.role = role;
//        this.doctors = doctors;
//    }
}
