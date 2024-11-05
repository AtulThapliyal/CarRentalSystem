package com.rental.CarRentalSystem.dto;

import com.rental.CarRentalSystem.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long userId;

    private String userName;

    private String userEmail;

    private String password;

    private UserRole userRole;
}
