package com.spring.bookMyDoctorApp.repository;

import com.spring.bookMyDoctorApp.entity.Appointment;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment,String> {

    List<Appointment> findByDoctorId(String doctorId);
}
