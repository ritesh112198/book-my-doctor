package com.spring.bookMyDoctorApp.entity;

//import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

//@Entity
@Document(collection = "leaveRequest")
@Component
@Data
public class LeaveRequest {

    //@Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String id;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String reason;

    //we store the user's ID as a reference
    private String userId;
    //many to one relation
   // @ManyToOne
  //  @JoinColumn(name = "user_id")
  //  private User user;

//    // Optional (for convenience)
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }

    public String getUserId() {
        return userId;
    }

}
