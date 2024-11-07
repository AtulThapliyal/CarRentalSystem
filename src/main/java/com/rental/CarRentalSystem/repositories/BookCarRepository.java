package com.rental.CarRentalSystem.repositories;

import com.rental.CarRentalSystem.entity.BookCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookCarRepository extends JpaRepository<BookCar, Long> {
    List<BookCar> findAllByUserUserId(Long userId);
}
