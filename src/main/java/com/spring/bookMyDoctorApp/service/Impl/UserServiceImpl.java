package com.spring.bookMyDoctorApp.service.Impl;

import com.spring.bookMyDoctorApp.dto.*;
import com.spring.bookMyDoctorApp.entity.Role;
import com.spring.bookMyDoctorApp.entity.User;
import com.spring.bookMyDoctorApp.exceptionHandling.LoginException;
import com.spring.bookMyDoctorApp.exceptionHandling.UserAlreadyExistedException;
import com.spring.bookMyDoctorApp.repository.UserRepository;
import com.spring.bookMyDoctorApp.service.AppointmentService;
import com.spring.bookMyDoctorApp.service.LeaveRequestService;
import com.spring.bookMyDoctorApp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    UserService userService;
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private LeaveRequestService leaveRequestService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto registerUser(UserDto userDto) {
        if (userRepository.findByUserEmail(userDto.getUserEmail()).isPresent()) {
            throw new UserAlreadyExistedException("User already exists with email: " + userDto.getUserEmail());
        }
        User user=modelMapper.map(userDto, User.class);
      //  user.set_id(UUID.randomUUID(.toString()); // Generate unique ID
        User savedUser=userRepository.save(user);

        return modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public LoginResponse loginUser(LoginRequest request) {
        Optional<User> optionalUser = userRepository.findByUserEmail(request.getEmail());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user.getUserPassword().equals(request.getPassword())) {
                // Prepare response
                LoginResponse response = new LoginResponse();
                response.setMessage("Login Successful");
                response.setRole(user.getRole());

                switch (user.getRole()) {
                    case DOCTOR:
                        // Doctor sees their own appointments
                        List<AppointmentDto> doctorAppointments = appointmentService
                                .getAppointmentsByDoctorId(user.get_id());
                        response.setAppointments(doctorAppointments);
                        break;

                    case ADMIN:
                        // Admin sees all doctors and all leave requests
                        List<UserDto> allDoctors = getAllDoctors();
                        List<LeaveRequestDto> allLeaveRequests = leaveRequestService.getAllLeaves();
                        response.setDoctors(allDoctors);
                        response.setLeaveRequests(allLeaveRequests);
                        break;

                    case PATIENT:
                        // Patient sees list of doctors
                        List<UserDto> availableDoctors = getAllDoctors();
                        response.setDoctors(availableDoctors);
                        break;

                    default:
                        throw new LoginException("No Specified Role: " + user.getRole());
                }
                return response;
            } else {
                throw new LoginException("Invalid username or password");
            }
        } else {
            throw new LoginException("User Not Found");
        }
    }

    @Override
    public List<UserDto> getAllDoctors(){
        List<User> doctors=userRepository.findByRole(Role.DOCTOR);
        return doctors.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUsersByLocation(String location){
        List<User> doctors=userRepository.findByRoleAndUserLocation(Role.DOCTOR,location);
        return doctors.stream()
                .map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
    }
    @Override
    public List<UserDto> getUsersBySpecialization(String specialization) {
        List<User> doctors = userRepository.findBySpecialization(specialization);
        return doctors.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> searchDoctorByLocationOrSpecialization( String location,String specialization) {
        List<User> doctor=userRepository.findDoctorByLocationOrSpecialization(location,specialization);
        return doctor.stream().map(doc->modelMapper.map(doc,UserDto.class)).collect(Collectors.toList());
    }
}
