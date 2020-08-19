package com.infor.karamat;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.infor.karamat.handler.CarService;
import com.infor.karamat.model.CarAvailability;
import com.infor.karamat.repository.CarAvailibilityRepository;

@RunWith(SpringRunner.class)
public class ServiceUnitTest {
	@InjectMocks
	CarService carService;

	@Mock
	CarAvailibilityRepository carAvRepo;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@Category(Unit.class)
	public void CarRepoTest9() throws InterruptedException, ExecutionException {
		CarAvailability carAv1 = new CarAvailability(1, "123321", LocalDateTime.parse("2020-08-10T10:20"),
				LocalDateTime.parse("2020-08-29T10:59"), 100);
		CarAvailability carAv2 = new CarAvailability(1, "456654", LocalDateTime.parse("2020-08-10T10:20"),
				LocalDateTime.parse("2020-08-29T10:59"), 100);
		CarAvailability carAv3 = new CarAvailability(1, "951159", LocalDateTime.parse("2020-08-10T10:20"),
				LocalDateTime.parse("2020-08-29T10:59"), 100);
		CarAvailability carAv4 = new CarAvailability(1, "357753", LocalDateTime.parse("2020-08-10T10:20"),
				LocalDateTime.parse("2020-08-29T10:59"), 100);

		List<CarAvailability> carAvList = new ArrayList<>();
		carAvList.add(carAv1);
		carAvList.add(carAv2);
		carAvList.add(carAv3);
		carAvList.add(carAv4);

		when(carAvRepo.findAll()).thenReturn(carAvList);

		// Test
		List<CarAvailability> avList = carService.findAllAvCars().get();
		assertEquals(avList.size(), 4);
		assertEquals(avList.get(1).getPlate(), "456654");
	}
}
