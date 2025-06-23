package com.spring.bookMyDoctorApp.exceptionHandling;

import com.spring.bookMyDoctorApp.config.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException e){
        ApiResponse ar=new ApiResponse();
        ar.setData(null);
        ar.setError("resource not found...");
        ar.setStatus(HttpStatus.NOT_FOUND);
        ar.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ar);
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ApiResponse> handleInvalidLoginCredentialsException(LoginException e) {
        ApiResponse ar = new ApiResponse();
        ar.setData(null);
        ar.setError("Authentication Failed"); // Clear error type
        ar.setStatus(HttpStatus.UNAUTHORIZED); // Use 401 Unauthorized
        ar.setMessage(e.getMessage()); // Use the message from the exception (e.g., "Invalid username or password")
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ar);
    }
    @ExceptionHandler(AppointmentException.class)
    public ResponseEntity<ApiResponse> handleInvalidAppointmentsException(AppointmentException e) {
        ApiResponse ar = new ApiResponse();
        ar.setData(null);
        ar.setError("Authentication Failed"); // Clear error type
        ar.setStatus(HttpStatus.UNAUTHORIZED); // Use 401 Unauthorized
        ar.setMessage(e.getMessage()); // Use the message from the exception (e.g., "Invalid username or password")
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ar);
    }
    @ExceptionHandler(UserAlreadyExistedException.class)
    public ResponseEntity<ApiResponse> handleUserAlreadyExistedException(UserAlreadyExistedException e){
        ApiResponse ar=new ApiResponse<>();
        ar.setData(null);
        ar.setError("User is There");
        ar.setMessage(e.getMessage());
        ar.setStatus(HttpStatus.NOT_ACCEPTABLE);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ar);
    }
}
