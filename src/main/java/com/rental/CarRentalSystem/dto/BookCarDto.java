package com.rental.CarRentalSystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rental.CarRentalSystem.entity.Car;
import com.rental.CarRentalSystem.entity.User;
import com.rental.CarRentalSystem.enums.BookCarStatus;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Data
public class BookCarDto {
    private Long id;

    private Date fromDate;

    private Date toDate;

    private Long days;

    private Long price;

    private BookCarStatus bookCarStatus;

   private Long carId;

   private Long userId;

   private String username;

   private String email;
}
