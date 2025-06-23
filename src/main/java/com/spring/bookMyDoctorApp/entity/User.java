package com.spring.bookMyDoctorApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.stereotype.Component;

import java.util.List;

//@Entity
@Document(collection = "users")//as per mongoDB
@AllArgsConstructor
@NoArgsConstructor
@Data
//@Table(name = "users")
@JsonIgnoreProperties({"appointmentsMade", "appointmentsReceived", "leaveRequests"})
public class User {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    String _id;
    String userName;
    String userPassword;
    String userEmail;
    long userPhoneNumber;
    String  userAge;
    String userLocation;

   // @Enumerated(EnumType.STRING)
    private Role role;

    //doctor
    private String specialization;
    private String experienceYears;
    
   // @OneToMany(mappedBy = "patient",cascade=CascadeType.ALL)
    private List<Appointment> appointmentsMade;

   // @OneToMany(mappedBy = "doctor",cascade=CascadeType.ALL)
    private List<Appointment> appointmentsReceived;

  //  @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<LeaveRequest> leaveRequests;

    public void setAge(int age) {
    }
}
