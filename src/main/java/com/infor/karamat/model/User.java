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
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;

	String name;
	LocalDateTime drivingLicenceExpireDate;
	String drivingLicencenumber;

	LocalDateTime membershipDate;
	String menebrshipType;
}
