package com.infor.karamat.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CarAvailability {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;

	String plate;
	
	LocalDateTime startDate;
	LocalDateTime endDate;
	
	double pricePerHour;
}
