package com.infor.karamat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infor.karamat.model.Car;

public interface CarRepository extends JpaRepository<Car, String> {

}
