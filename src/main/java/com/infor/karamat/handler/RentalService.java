package com.infor.karamat.handler;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.google.common.collect.ComputationException;
import com.infor.karamat.model.CarAvailability;
import com.infor.karamat.model.Rental;
import com.infor.karamat.repository.CarAvailibilityRepository;
import com.infor.karamat.repository.RentalRepository;
import com.infor.karamat.util.NoResourceException;

import io.netty.util.concurrent.CompleteFuture;

@Service
public class RentalService {
	// minimum time can be register as car is available
	static long minGap = 15;

	@Autowired
	private RentalRepository rentalRepo;

	@Autowired
	private CarAvailibilityRepository carAvRepo;

	@Async
	public CompletableFuture<Rental> book(String plate, int userId, LocalDateTime startDate, LocalDateTime endDate) {
		List<CarAvailability> carAvList = carAvRepo.findByPlate(plate);
		
		if (!carAvList.isEmpty()) {
			Optional<CarAvailability> result = carAvList.stream().filter(item -> 
			(item.getStartDate().isBefore(startDate) && item.getEndDate().isAfter(endDate)))
			.findFirst();
			if (result.isPresent()){
				CarAvailability resultContent = result.get();
				double duration = Duration.between(startDate, endDate).toHours();

				double totalPayment = resultContent.getPricePerHour() * duration;
				//Update the Availability table
				carAvRepo.deleteById(resultContent.getId());

				if (Duration.between(resultContent.getStartDate(), startDate).toMinutes() > minGap)
					carAvRepo.save(
							new CarAvailability(0, plate, resultContent.getStartDate(), startDate, resultContent.getPricePerHour()));
				if (Duration.between(endDate, resultContent.getEndDate()).toMinutes() > minGap)
					carAvRepo.save(
							new CarAvailability(0, plate, endDate, resultContent.getEndDate(), resultContent.getPricePerHour()));
				return  CompletableFuture.completedFuture(rentalRepo.save(new Rental(0, plate, userId, startDate, endDate, duration, totalPayment)));			
			}

		} else {
			throw new NoResourceException("car with this plate does not exist " + plate);
		}
		return null;		
	}

	@Async
	public CompletableFuture<List<Rental>> bookedList(LocalDateTime startDate, LocalDateTime endDate) {
		return CompletableFuture.completedFuture(rentalRepo.findByStartDateAfterAndEndDateBefore(startDate, endDate));
	}

//	@Async
//	public CompletableFuture<List<Rental>> bookedListNumber(LocalDateTime startDate, LocalDateTime endDate) {
//		return CompletableFuture.completedFuture(rentalRepo.findByStartDateAfterAndEndDateBefore(startDate, endDate));
//	}

	@Async
	public CompletableFuture<Double> bookedListPerHour(LocalDateTime startDate, LocalDateTime endDate) {
		double duration = Duration.between(startDate, endDate).toHours();
		return CompletableFuture
				.completedFuture((rentalRepo.countByStartDateAfterAndEndDateBefore(startDate, endDate)) / duration);
	}

	public CompletableFuture<Double> reportPayment(LocalDateTime startDate, LocalDateTime endDate) {
		return CompletableFuture.completedFuture(rentalRepo.findByStartDateAfterAndEndDateBefore(startDate, endDate)
				.stream().mapToDouble(item -> item.getTotalPayment()).sum());
	}

}
