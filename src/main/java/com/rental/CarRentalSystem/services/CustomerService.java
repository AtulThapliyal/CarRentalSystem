package com.rental.CarRentalSystem.services;

import com.rental.CarRentalSystem.dto.BookCarDto;
import com.rental.CarRentalSystem.dto.CarRequestDto;
import com.rental.CarRentalSystem.entity.Car;

import java.util.List;

public interface CustomerService {

    List<CarRequestDto> getAllCarsList();

    boolean bookCar(BookCarDto bookCarDto);

    CarRequestDto getCarDetails(Long carId);

    List<BookCarDto> getBookedCarByUserId(Long userId);
}
