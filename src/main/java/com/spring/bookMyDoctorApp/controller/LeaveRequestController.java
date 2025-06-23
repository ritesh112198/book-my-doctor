package com.spring.bookMyDoctorApp.controller;

import com.spring.bookMyDoctorApp.dto.LeaveRequestDto;
import com.spring.bookMyDoctorApp.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaves")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    @PostMapping("/apply")
   public ResponseEntity<String> applyLeave(@RequestBody LeaveRequestDto leaveRequestDto){
        leaveRequestService.applyLeave(leaveRequestDto);
        return ResponseEntity.ok("Leave request submitted Successfully.");
    }

    @GetMapping("/getLeaves")
    public List<LeaveRequestDto> getAllLeaves(){
        return leaveRequestService.getAllLeaves();
    }
}
