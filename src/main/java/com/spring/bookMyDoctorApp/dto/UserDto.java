package com.spring.bookMyDoctorApp.dto;

import com.spring.bookMyDoctorApp.entity.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    @NotBlank(message = "Username is required")
    private String userName;

    @NotBlank(message = "Password is required")
    @Size(min = 4, message = "Password must be at least 4 characters")
    private String userPassword;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String userEmail;

    @Min(value = 1000000000, message = "Phone number must be at least 10 digits")
    @Max(value = 9999999999L, message = "Phone number must be at most 10 digits")
    private long userPhoneNumber;

    @NotBlank(message = "Age is required")
    @Pattern(regexp = "\\d+", message = "Age must be a number")
    private String userAge;

    @NotBlank(message = "Location is required")
    private String userLocation;

    @NotNull(message = "Role must be specified")
    private Role role;

    // These can be optional based on role
    private String specialization;

    private String experienceYears;
}
