package com.rental.CarRentalSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rental.CarRentalSystem.dto.BookCarDto;
import com.rental.CarRentalSystem.enums.BookCarStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Data
@Entity
@Table(name = "car-book")
public class BookCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fromDate;

    private Date toDate;

    private Long days;

    private Long price;

    private BookCarStatus bookCarStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "car_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Car car;


    public BookCarDto getBookCarDto(){
        BookCarDto bookCarDto = new BookCarDto();

        bookCarDto.setId(id);
        bookCarDto.setDays(days);
        bookCarDto.setBookCarStatus(bookCarStatus);
        bookCarDto.setPrice(price);
        bookCarDto.setToDate(toDate);
        bookCarDto.setFromDate(fromDate);
        bookCarDto.setEmail(user.getUserEmail());
        bookCarDto.setUsername(user.getUsername());
        bookCarDto.setUserId(user.getUserId());
        bookCarDto.setCarId(car.getCarId());

        return bookCarDto;

    }
}
