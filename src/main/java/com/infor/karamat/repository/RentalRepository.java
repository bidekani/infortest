package com.infor.karamat.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infor.karamat.model.Rental;

public interface RentalRepository extends JpaRepository<Rental, Integer>{
	int  countByStartDateAfterAndEndDateBefore(LocalDateTime startDate, LocalDateTime endDate);
	List<Rental> findByStartDateAfterAndEndDateBefore(LocalDateTime startDate, LocalDateTime endDate);
}
