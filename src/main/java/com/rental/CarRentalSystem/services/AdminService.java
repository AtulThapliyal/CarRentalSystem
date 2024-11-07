package com.rental.CarRentalSystem.services;


import com.rental.CarRentalSystem.dto.CarRequestDto;
import com.rental.CarRentalSystem.entity.Car;

import java.io.IOException;
import java.util.List;

public interface AdminService {

    boolean postCar(CarRequestDto carRequest) throws IOException;

    List<Car> getAllCars();

    void deleteCar(Long id);

    CarRequestDto getCarById(Long id);

    boolean updateCar(Long id, CarRequestDto carRequestDto) throws IOException;
}
