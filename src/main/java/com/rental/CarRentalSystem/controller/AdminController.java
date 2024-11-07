package com.rental.CarRentalSystem.controller;

import com.rental.CarRentalSystem.dto.CarRequestDto;
import com.rental.CarRentalSystem.services.AdminService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/car")
    public ResponseEntity<?> postCars(@RequestBody CarRequestDto carRequestDto) throws IOException {

        boolean success = adminService.postCar(carRequestDto);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body("The car is added !");
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Car not added !");
    }

    @GetMapping("/car")
    public ResponseEntity<?> getAllCars() {
        return ResponseEntity.ok(adminService.getAllCars());
    }

    @DeleteMapping("/car/{id}")
    public void deleteCar(@PathVariable Long id) {
        adminService.deleteCar(id);
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<?> getCarDetailsById(Long id) {
        CarRequestDto carDto = adminService.getCarById(id);
        return ResponseEntity.ok(carDto);
    }

    @PutMapping("/car/{carId}")
    public ResponseEntity<Void> updateCarDetails(@PathVariable Long carId, @RequestBody CarRequestDto carDetails) throws IOException {
        try {
            boolean success = adminService.updateCar(carId, carDetails);

            if (success) return ResponseEntity.status(HttpStatus.OK).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
