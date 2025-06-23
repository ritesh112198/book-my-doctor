package com.spring.bookMyDoctorApp.controller;

import com.spring.bookMyDoctorApp.config.ApiResponse;
import com.spring.bookMyDoctorApp.dto.LoginRequest;
import com.spring.bookMyDoctorApp.dto.LoginResponse;
import com.spring.bookMyDoctorApp.dto.RegisterResponse;
import com.spring.bookMyDoctorApp.dto.UserDto;
//import com.spring.bookMyDoctorApp.entity.User;
import com.spring.bookMyDoctorApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@Valid @RequestBody UserDto dto) {
        UserDto registeredUser = userService.registerUser(dto);
        String message = "Registered successfully as a " + registeredUser.getRole().name();
        RegisterResponse response = new RegisterResponse(message, registeredUser.getRole().name());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUsers(@Valid @RequestBody LoginRequest loginRequest){
        return  ResponseEntity.ok(userService.loginUser(loginRequest));
    }
    @GetMapping("doctors")
   public ResponseEntity<List<UserDto>> getDoctors(){
        return ResponseEntity.ok(userService.getAllDoctors());
    }
    @GetMapping("/doctors/location/{location}")
    public ResponseEntity<List<UserDto>> getDoctorsByLocation(@Valid @PathVariable String location){
        return ResponseEntity.ok(userService.getUsersByLocation(location));
    }
    @GetMapping("/doctors/specialization/{specialization}")
    public ResponseEntity<List<UserDto>> getDoctorsBySpecialization(@Valid @PathVariable String specialization) {
        List<UserDto> doctors = userService.getUsersBySpecialization(specialization);
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchDoctor(@RequestParam(required = false) String location,@RequestParam(required = false) String specialization){
        List<UserDto>  doctors=userService.searchDoctorByLocationOrSpecialization(location,specialization);
        ApiResponse response = new ApiResponse<>(
                HttpStatus.OK,
                null, // error
                doctors, // data
                "Search Results for: "
        );
        return ResponseEntity.ok(response);
    }
}
