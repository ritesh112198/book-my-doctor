package com.spring.bookMyDoctorApp.service;

import com.spring.bookMyDoctorApp.dto.LoginRequest;
import com.spring.bookMyDoctorApp.dto.LoginResponse;
import com.spring.bookMyDoctorApp.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto registerUser(UserDto userDto);


    LoginResponse loginUser(LoginRequest loginRequest);

    List<UserDto> getAllDoctors();


    List<UserDto> getUsersByLocation(String location);

    //List<UserDto> getUsersBySpeciality(String speciality);

    List<UserDto> getUsersBySpecialization(String specialization);

    List<UserDto> searchDoctorByLocationOrSpecialization(String location,String specialization);
}
