package com.infor.karamat.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infor.karamat.model.CarAvailability;

public interface CarAvailibilityRepository extends JpaRepository<CarAvailability, Integer> {
	List<CarAvailability> findByPlate(String plate);
	List<CarAvailability> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDateTime startDate, LocalDateTime endDate);
}
