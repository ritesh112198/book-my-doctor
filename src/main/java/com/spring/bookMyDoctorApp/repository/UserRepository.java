package com.spring.bookMyDoctorApp.repository;

import com.spring.bookMyDoctorApp.entity.Role;
import com.spring.bookMyDoctorApp.entity.User;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUserEmail(String email);

    List<User> findByRole(Role role);

    List<User> findByRoleAndUserLocation(Role role, String userLocation);

    List<User> findBySpecialization(String specialization);

    @Query("{ '$or': [ { 'userLocation': ?0 }, { 'specialization': ?1 } ] }")//MongoDB query
    List<User> findDoctorByLocationOrSpecialization(String userLocation,String specialization);

}