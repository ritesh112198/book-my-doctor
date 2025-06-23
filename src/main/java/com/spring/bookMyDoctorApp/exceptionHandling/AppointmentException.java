package com.spring.bookMyDoctorApp.exceptionHandling;

public class AppointmentException extends RuntimeException{
    public AppointmentException(String message){
        super(message);
    }
}
