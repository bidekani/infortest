package com.infor.karamat.resource;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infor.karamat.handler.RentalService;
import com.infor.karamat.model.Rental;

import io.swagger.annotations.ApiOperation;


@RequestMapping("/v1/rental")
@RestController


/*
 * Book a car from date/time, to date/time, user id, car id Report of booked
 * cars from date/time, to date/time Report of number of booked cars per hour
 * from date, to date Report of total payment from date, to date
 */

public class RentalResource {
	@Autowired
	private RentalService rentalService;

	@RequestMapping(value = "/book/{plate}/{userId}", method = RequestMethod.GET, produces = { "application/json" })
	@ApiOperation(value = "book a car")
	protected Rental bookCar(@PathVariable(value = "plate") String carId, @PathVariable(value = "userId") int userId,
			@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) throws Throwable {
		return rentalService.book(carId, userId, startDate, endDate).get();
	}

	@RequestMapping(value = "/report/booked", method = RequestMethod.GET, produces = { "application/json" })
	@ApiOperation(value = "Report Booked cars")
	protected List<Rental> reportBookedCar(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate)
			throws Throwable {
		try {
			return rentalService.bookedList(startDate, endDate).get();
		} catch (Throwable e) {
			throw e.getCause();
		}
	}

	@RequestMapping(value = "/report/numbers", method = RequestMethod.GET, produces = { "application/json" })
	@ApiOperation(value = "Report number of Booked cars per hour")
	protected double reportBookedPrHour(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate)
			throws Throwable {
		return rentalService.bookedListPerHour(startDate, endDate).get();
	}

	@RequestMapping(value = "/report/payment", method = RequestMethod.GET, produces = { "application/json" })
	@ApiOperation(value = "Report of total payment from date, to date")
	protected double reportPayment(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate)
			throws Throwable {
		return rentalService.reportPayment(startDate, endDate).get();
	}

}
