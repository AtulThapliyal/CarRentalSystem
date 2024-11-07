package com.rental.CarRentalSystem.services.impl;

import com.rental.CarRentalSystem.dto.BookCarDto;
import com.rental.CarRentalSystem.dto.CarRequestDto;
import com.rental.CarRentalSystem.entity.BookCar;
import com.rental.CarRentalSystem.entity.Car;
import com.rental.CarRentalSystem.entity.User;
import com.rental.CarRentalSystem.enums.BookCarStatus;
import com.rental.CarRentalSystem.repositories.BookCarRepository;
import com.rental.CarRentalSystem.repositories.CarRepository;
import com.rental.CarRentalSystem.repositories.UserRepository;
import com.rental.CarRentalSystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CarRepository carRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookCarRepository bookCarRepository;

    @Override
    public List<CarRequestDto> getAllCarsList() {
       return carRepository.findAll().stream().map(Car::getCarDto).toList();
    }

    @Override
    public boolean bookCar(BookCarDto bookCarDto) {
        Optional<Car> carOptional = carRepository.findById(bookCarDto.getCarId());
        Optional<User> userOptional = userRepository.findById(bookCarDto.getUserId());

        if(carOptional.isPresent() && userOptional.isPresent()){
            BookCar bookCar = new BookCar();

            bookCar.setUser(userOptional.get());
            bookCar.setCar(carOptional.get());
            bookCar.setBookCarStatus(BookCarStatus.PENDING);
            long diffInMillis =bookCarDto.getToDate().getTime() - bookCarDto.getFromDate().getTime();

            long days = TimeUnit.MICROSECONDS.toDays(diffInMillis);

            bookCar.setDays(days);
            bookCar.setPrice(carOptional.get().getPrice()* days);

            bookCarRepository.save(bookCar);

            return  true;
        }
        return false;
    }

    @Override
    public CarRequestDto getCarDetails(Long carId) {
        Optional<Car> optionalCar = carRepository.findById(carId);

        return optionalCar.map(Car::getCarDto).orElse(null);

    }

    @Override
    public List<BookCarDto> getBookedCarByUserId(Long userId) {
        return bookCarRepository.findAllByUserUserId(userId).stream().map(BookCar::getBookCarDto).toList();
    }
}
