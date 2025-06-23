package com.spring.bookMyDoctorApp.entity;

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Document(collection = "appointments")
public class Appointment {
   // @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String id;

    private LocalDateTime appointmentTime;

//
//    @ManyToOne
//    @JoinColumn(name="patient_id")
    private User patientId;

//    @ManyToOne
//    @JoinColumn(name = "doctors_id")
    private User doctorId;

}
