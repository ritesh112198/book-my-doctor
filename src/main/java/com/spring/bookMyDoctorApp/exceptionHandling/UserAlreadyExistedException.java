package com.spring.bookMyDoctorApp.exceptionHandling;

public class UserAlreadyExistedException extends RuntimeException{
    public UserAlreadyExistedException(String message){
        super(message);
    }
}
