package com.infor.karamat.resource;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infor.karamat.handler.CarService;
import com.infor.karamat.model.Car;
import com.infor.karamat.model.CarAvailability;

import io.swagger.annotations.ApiOperation;

@RequestMapping("/v1/cars")
@RestController
/*
 * Register a car with its plate number Register availability of a car from
 * date/time, to date/time, rental price per hour Search for available cars to
 * rent from date/time, to date/time, maximum rental price
 * 
 * + car CRUD and caravailibility CRUD
 */
public class CarResource {
	@Autowired
	private CarService carService;

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = { "application/json" })
	@ApiOperation(value = "list all cars")
	protected List<Car> listAll() throws Throwable {
		try {
			return carService.listAll().get();
		} catch (Throwable e) {
			throw new Throwable(e.getCause());
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT, produces = { "application/json" })
	@ApiOperation(value = "Update a car")
	protected Car updateCar(@RequestBody Car car) throws Throwable {
		try {
			return carService.regsiterCar(car).get();
		} catch (Exception e) {
			throw new Throwable(e.getCause());
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = { "application/json" })
	@ApiOperation(value = "Register New Car")
	protected Car registerCar(@RequestBody Car car) throws Throwable {
		try {
			return carService.regsiterCar(car).get();
		} catch (Exception e) {
			throw new Throwable(e.getCause());
		}
				
		}

	@RequestMapping(value = "/{plate}", method = RequestMethod.DELETE, produces = { "application/json" })
	@ApiOperation(value = "Delete a car")
	protected Car deleteCar(@RequestParam("plate") String plate) {
		this.addCars();
		return carService.deleteCar(plate);
	}

	@RequestMapping(value = "/availibity", method = RequestMethod.POST, produces = { "application/json" })
	@ApiOperation(value = "Register availability of a car")
	protected CarAvailability carAvailability(@RequestBody CarAvailability carAvailibility) throws Throwable {
//		return carAvailibility;
		try {
			return carService.registerCarAvailability(carAvailibility).get();
		} catch (Exception e) {
			throw new Throwable(e.getCause());
		}
	}

	@RequestMapping(value = "/availibity/filter", method = RequestMethod.GET, produces = { "application/json" })
	@ApiOperation(value = "search for available cars")
	protected List<CarAvailability> findCars(@RequestParam LocalDateTime fromDate, @RequestParam LocalDateTime toDate,
			@RequestParam double budget) throws Throwable {
		try {
			return carService.findCars(fromDate, toDate, budget).get();
		} catch (Exception e) {
			throw new Throwable(e.getCause());
		}
	}

	@RequestMapping(value = "/availibity/{plate}", method = RequestMethod.GET, produces = { "application/json" })
	@ApiOperation(value = "search for available cars")
	protected List<CarAvailability> findAvailbleCars(@PathVariable("plate") String plate) throws Throwable {
		try {
			return carService.findCarAvailability(plate).get();
		} catch (Exception e) {
			throw new Throwable(e.getCause());
		}

	}

	@RequestMapping(value = "/availibity", method = RequestMethod.GET, produces = { "application/json" })
	@ApiOperation(value = "list all available cars")
	protected List<CarAvailability> findAllAvCars() throws Throwable {
		try {
			return carService.findAllAvCars().get();
		} catch (Exception e) {
			throw new Throwable(e.getCause());
		}
	}

	public void addCars() {
		Car car1 = new Car("112233", "VOLVO", "XC40", 2019, 1500, "balck");
		carService.regsiterCar(car1);

		Car car2 = new Car("55GG12", "VOLVO", "XC60", 2019, 1500, "white");
		carService.regsiterCar(car2);

		Car car3 = new Car("GEH1055", "VOLVO", "XC90", 2019, 1500, "balck");
		carService.regsiterCar(car3);

		Car car4 = new Car("ABF951", "VOLVO", "v40", 2019, 1500, "balck");
		carService.regsiterCar(car4);

		Car car5 = new Car("BGV753", "VOLVO", "v60", 2019, 1500, "balck");
		carService.regsiterCar(car5);

		Car car6 = new Car("PKL258", "VOLVO", "v90", 2019, 1500, "balck");
		carService.regsiterCar(car6);

	}

}
