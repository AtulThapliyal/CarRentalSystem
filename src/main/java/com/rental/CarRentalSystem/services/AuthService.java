package com.rental.CarRentalSystem.services;

import com.rental.CarRentalSystem.dto.AuthenticationRequest;
import com.rental.CarRentalSystem.dto.AuthenticationResponse;
import com.rental.CarRentalSystem.dto.SignUpRequest;
import com.rental.CarRentalSystem.dto.UserDto;
import com.rental.CarRentalSystem.entity.User;

public interface AuthService {

    UserDto createUser(SignUpRequest signUpRequest);

    boolean hasCustomerWithEmail(String email);

    AuthenticationResponse getUserResponse(AuthenticationRequest authenticationRequest);
}
