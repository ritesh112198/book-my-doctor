package com.spring.bookMyDoctorApp.exceptionHandling;

public class LoginException extends RuntimeException{
    public LoginException(String message){
        super(message);
    }
}
