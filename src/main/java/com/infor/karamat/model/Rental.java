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
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;

	String carPlate;
	int userId;

	LocalDateTime startDate;
	LocalDateTime endDate;

	double duration;
	double totalPayment;
}
