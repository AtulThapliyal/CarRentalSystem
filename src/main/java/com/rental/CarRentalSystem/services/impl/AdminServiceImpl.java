package com.rental.CarRentalSystem.services.impl;

import com.rental.CarRentalSystem.dto.CarRequestDto;
import com.rental.CarRentalSystem.entity.Car;
import com.rental.CarRentalSystem.repositories.CarRepository;
import com.rental.CarRentalSystem.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public boolean postCar(CarRequestDto carRequest) throws IOException {
        try{
            Car car = new Car();
            car.setName(carRequest.getName());
            car.setBrand(carRequest.getBrand());
            car.setColor(carRequest.getColor());
            car.setPrice(carRequest.getPrice());
            car.setYear(carRequest.getYear());
            car.setType(carRequest.getType());
            car.setDescription(carRequest.getDescription());
            car.setTransmission(carRequest.getTransmission());
            car.setImage(carRequest.getImage().getBytes());
            carRepository.save(car);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public void deleteCar(Long id){
        carRepository.deleteById(id);
    }

    @Override
    public CarRequestDto getCarById(Long id) {

        Optional<Car> carOptional = carRepository.findById(id);
        return carOptional.map(Car::getCarDto).orElse(null);
    }

    @Override
    public boolean updateCar(Long id, CarRequestDto carRequestDto) throws IOException {
        Optional<Car> carOptional = carRepository.findById(id);
        if(carOptional.isPresent()){
            Car existingCar = carOptional.get();
            if(carRequestDto.getImage() != null){

                existingCar.setImage(carRequestDto.getImage().getBytes());
            }
            existingCar.setPrice(carRequestDto.getPrice());
            existingCar.setYear(carRequestDto.getYear());
            existingCar.setType(carRequestDto.getType());
            existingCar.setDescription(carRequestDto.getDescription());
            existingCar.setTransmission(carRequestDto.getTransmission());
            existingCar.setColor(carRequestDto.getColor());
            existingCar.setName(carRequestDto.getName());
            existingCar.setBrand(carRequestDto.getBrand());
            carRepository.save(existingCar);
            return true;
        }
        return false;
    }
}
