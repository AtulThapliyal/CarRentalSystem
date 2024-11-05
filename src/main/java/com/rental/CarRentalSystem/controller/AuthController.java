package com.rental.CarRentalSystem.controller;

import com.rental.CarRentalSystem.dto.AuthenticationRequest;
import com.rental.CarRentalSystem.dto.AuthenticationResponse;
import com.rental.CarRentalSystem.dto.SignUpRequest;
import com.rental.CarRentalSystem.dto.UserDto;
import com.rental.CarRentalSystem.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/signup" )
    public ResponseEntity<?> signUpCustomer(@RequestBody SignUpRequest signUpRequest){
        if(authService.hasCustomerWithEmail(signUpRequest.getUserEmail())) return new ResponseEntity<>("Customer Already Exists", HttpStatus.NOT_ACCEPTABLE );
        UserDto createdUser = authService.createUser(signUpRequest);
        if(createdUser == null) return new ResponseEntity<>("Customer not Created! Try again later !!", HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws BadCredentialsException, DisabledException, UsernameNotFoundException {
        try{
            return authService.getUserResponse(authenticationRequest);
        }catch (Exception e){
            throw  new UsernameNotFoundException("Not found");
        }
    }
}
