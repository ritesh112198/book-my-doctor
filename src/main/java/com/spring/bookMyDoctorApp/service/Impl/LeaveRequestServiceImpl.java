package com.spring.bookMyDoctorApp.service.Impl;

import com.spring.bookMyDoctorApp.dto.LeaveRequestDto;
import com.spring.bookMyDoctorApp.entity.LeaveRequest;
import com.spring.bookMyDoctorApp.entity.User;
import com.spring.bookMyDoctorApp.exceptionHandling.ResourceNotFoundException;
import com.spring.bookMyDoctorApp.repository.LeaveRequestRepository;
import com.spring.bookMyDoctorApp.repository.UserRepository;
import com.spring.bookMyDoctorApp.service.LeaveRequestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LeaveRequestDto applyLeave(LeaveRequestDto lvDto) {
        LeaveRequest leaveRequest = modelMapper.map(lvDto, LeaveRequest.class);

        // MongoRepository uses String ID type
        User user = userRepository.findById(String.valueOf(lvDto.getUserId()))
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Check enum value correctly
        if (!user.getRole().name().equalsIgnoreCase("DOCTOR")) {
            throw new ResourceNotFoundException("Only doctors can apply for leave...");
        }

        leaveRequest.setUserId(user.get_id());

        LeaveRequest savedRequest = leaveRequestRepository.save(leaveRequest);
        return modelMapper.map(savedRequest, LeaveRequestDto.class);
    }

    @Override
    public List<LeaveRequestDto> getAllLeaves(){
        return leaveRequestRepository.findAll().stream()
                .map(leaveRequest -> {
                    LeaveRequestDto dto = modelMapper.map(leaveRequest, LeaveRequestDto.class);
                    if (leaveRequest.getUserId() != null) {
                        dto.setUserId(leaveRequest.getUserId());
                    }
                    return dto;
                }).toList();
    }
}