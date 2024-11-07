package com.rental.CarRentalSystem.controller;

import com.rental.CarRentalSystem.dto.BookCarDto;
import com.rental.CarRentalSystem.dto.CarRequestDto;
import com.rental.CarRentalSystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")

public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/cars")
    public ResponseEntity<List<CarRequestDto>> getAllCars(){
        List<CarRequestDto> carList = customerService.getAllCarsList();
        return ResponseEntity.ok(carList);
    }

    @PostMapping("/car/book")
    public ResponseEntity<Void> bookACar(@RequestBody BookCarDto bookCarDto){
        boolean success = customerService.bookCar(bookCarDto);
        if(success) return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<CarRequestDto> getCarDetails(@PathVariable Long carId){

        CarRequestDto carDto = customerService.getCarDetails(carId);
        if (carDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(carDto);
    }

    @GetMapping("/car/bookings/{userId}")
    public  ResponseEntity<List<BookCarDto>> getBookedCars(@PathVariable Long userId){
        return ResponseEntity.ok(customerService.getBookedCarByUserId(userId));
    }
}
