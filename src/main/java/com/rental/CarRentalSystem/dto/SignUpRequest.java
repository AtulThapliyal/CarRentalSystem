package com.rental.CarRentalSystem.dto;

import com.rental.CarRentalSystem.enums.UserRole;
import lombok.Data;

@Data
public class SignUpRequest {
    private String userEmail;

    private String password;

    private String userName;
}
