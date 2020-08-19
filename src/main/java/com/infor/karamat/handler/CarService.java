package com.infor.karamat.handler;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.infor.karamat.model.Car;
import com.infor.karamat.model.CarAvailability;
import com.infor.karamat.repository.CarAvailibilityRepository;
import com.infor.karamat.repository.CarRepository;
import com.infor.karamat.util.NoResourceException;

//import lombok.extern.slf4j.Slf4j;

@Service
//@Slf4j
public class CarService {
	@Autowired
	private CarRepository carRepository;

	@Autowired
	private CarAvailibilityRepository carAvailibilityRepo;

	@Async
	public CompletableFuture<Car> regsiterCar(Car car) {
		return CompletableFuture.completedFuture(carRepository.save(car));
	}
	
//	@Async
	public Car regsiterCar1(Car car) {
		return carRepository.save(car);
	}

	@Async
	public CompletableFuture<List<Car>> listAll() {
		System.out.println("list fired....");
		return CompletableFuture.completedFuture(carRepository.findAll());
	}

	public Car deleteCar(String plate) {
		return carRepository.findById(plate)
				.orElseThrow(() -> new NoResourceException("Can not find car with given plate :" + plate));
	}

	@Async
	public CompletableFuture<CarAvailability> registerCarAvailability(CarAvailability carAvailbility) {
		return CompletableFuture.completedFuture(carAvailibilityRepo.save(carAvailbility));
	}

	@Async
	public CompletableFuture<List<CarAvailability>> findCars(LocalDateTime startDate, LocalDateTime endDate, double budget) {
		double duration = Duration.between(startDate, endDate).toHours();
		List<CarAvailability> carAvList = carAvailibilityRepo.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(startDate, endDate);
		List<CarAvailability> result = carAvList.stream().filter(item->(item.getPricePerHour()*duration<=budget)).collect(Collectors.toList());
				return CompletableFuture.completedFuture(result);
	}

	@Async
	public CompletableFuture<List<CarAvailability>> findCarAvailability(String plate) {
		return CompletableFuture.completedFuture(carAvailibilityRepo.findByPlate(plate));
	}

	@Async
	public CompletableFuture<List<CarAvailability>> findAllAvCars() {
		return CompletableFuture.completedFuture(carAvailibilityRepo.findAll());
	}

}
