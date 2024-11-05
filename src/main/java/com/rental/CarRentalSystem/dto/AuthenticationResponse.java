package com.rental.CarRentalSystem.dto;

import com.rental.CarRentalSystem.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private String jwt;

    private UserRole userRole;

    private Long userId;
}
