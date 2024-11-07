package com.rental.CarRentalSystem.entity;

import com.rental.CarRentalSystem.dto.CarRequestDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "cars")
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    private String brand;

    private String color;

    private String name;

    private String type;

    private String transmission;

    private String description;

    private Long price;

    private Date year;

    @Column(columnDefinition = "bytea")
    private byte[] image;

    public CarRequestDto getCarDto(){
        CarRequestDto carRequestDto = new CarRequestDto();
        carRequestDto.setName(name);
        carRequestDto.setColor(color);
        carRequestDto.setBrand(brand);
        carRequestDto.setDescription(description);
        carRequestDto.setPrice(price);
        carRequestDto.setType(type);
        carRequestDto.setYear(year);
        carRequestDto.setTransmission(transmission);
        carRequestDto.setReturnedImage(image);

        return carRequestDto;
    }
}
