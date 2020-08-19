package com.infor.karamat.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Car {
	@Id
	String plate;

	String brand;
	String model;
	int productionYear;
	int milage;
	String color;
}
