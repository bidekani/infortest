package com.infor.karamat;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.infor.karamat.model.Car;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IntegrationTest {
	@Autowired
	private WebTestClient webTestClient;

	@Test
	@Category(Acceptance.class)
	public void test8() {
		Car car = new Car("159951", "brand", "model", 2018, 159, "color");

		webTestClient.post().uri("/v1/cars/")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)				
			.body(Mono.just(car), Car.class)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$.plate").isNotEmpty()
			.jsonPath("$.plate").isEqualTo("159951");

		this.webTestClient.get().uri("/v1/cars/list/")
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$.length()").isEqualTo(1)
			.jsonPath("$[0].plate").isEqualTo("159951")
			.jsonPath("$[0].brand").isEqualTo("brand")
			.jsonPath("$[0].model").isEqualTo("model");
	}

}
