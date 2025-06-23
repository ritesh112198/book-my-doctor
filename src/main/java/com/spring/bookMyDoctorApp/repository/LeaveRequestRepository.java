package com.spring.bookMyDoctorApp.repository;

import com.spring.bookMyDoctorApp.entity.LeaveRequest;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRequestRepository extends MongoRepository<LeaveRequest,String> {

}
