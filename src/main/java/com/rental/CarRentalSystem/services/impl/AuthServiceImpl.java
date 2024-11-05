package com.rental.CarRentalSystem.services.impl;

import com.rental.CarRentalSystem.dto.AuthenticationRequest;
import com.rental.CarRentalSystem.dto.AuthenticationResponse;
import com.rental.CarRentalSystem.dto.SignUpRequest;
import com.rental.CarRentalSystem.dto.UserDto;
import com.rental.CarRentalSystem.entity.User;
import com.rental.CarRentalSystem.enums.UserRole;
import com.rental.CarRentalSystem.repositories.UserRepository;
import com.rental.CarRentalSystem.services.AuthService;
import com.rental.CarRentalSystem.services.UserService;
import com.rental.CarRentalSystem.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;


    @Override
    public UserDto createUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.setUserEmail(signUpRequest.getUserEmail());
        user.setUserName(signUpRequest.getUserName());
        user.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);
        User savedUser = userRepository.save(user);
        UserDto userDto = new UserDto();

        userDto.setUserId(savedUser.getUserId());
        return userDto;
    }

    @Override
    public boolean hasCustomerWithEmail(String email) {
        return userRepository.findFirstByUserEmail(email).isPresent();
    }

    @Override
    public AuthenticationResponse getUserResponse(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect Username Password");
        }

        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser = userRepository.findFirstByUserEmail(userDetails.getUsername());

        final String jwtToken = jwtUtil.generateToken(userDetails);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        if (optionalUser.isPresent()) {
            authenticationResponse.setJwt(jwtToken);
            authenticationResponse.setUserId(optionalUser.get().getUserId());
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
        }

        return authenticationResponse;
    }
}
