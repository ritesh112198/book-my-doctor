package com.spring.bookMyDoctorApp.service;

import com.spring.bookMyDoctorApp.dto.LeaveRequestDto;

import java.util.List;

public interface LeaveRequestService {

    LeaveRequestDto applyLeave(LeaveRequestDto lDto);
    List<LeaveRequestDto> getAllLeaves();

}
