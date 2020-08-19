package com.infor.karamat.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infor.karamat.model.Car;

@Service
public  class Init {
@Autowired
static UserService uservice;

@Autowired
static CarService carService;


public static void addUsers() {
//	User user1 = new User(0,"user1",new Date(),"",new Date(),"");
//	User user2 = new User(0,"user2",new Date(),"",new Date(),"");
//	User user3 = new User(0,"user3",new Date(),"",new Date(),"");
//	User user4 = new User(0,"user4",new Date(),"",new Date(),"");
//	uservice.add(user1);
//	uservice.add(user2);
//	uservice.add(user3);
//	uservice.add(user4);
		
}


public static void addCars() {
	Car car1=new Car("112233","VOLVO","XC40",2019,1500,"balck");
	carService.regsiterCar(car1);
	
	Car car2=new Car("55GG12","VOLVO","XC60",2019,1500,"white");
	carService.regsiterCar(car2);
	
	Car car3=new Car("GEH1055","VOLVO","XC90",2019,1500,"balck");
	carService.regsiterCar(car3);
	
	Car car4=new Car("ABF951","VOLVO","v40",2019,1500,"balck");
	carService.regsiterCar(car4);
	
	Car car5=new Car("BGV753","VOLVO","v60",2019,1500,"balck");
	carService.regsiterCar(car5);
	
	Car car6=new Car("PKL258","VOLVO","v90",2019,1500,"balck");
	carService.regsiterCar(car6);
	
	
}
	
}
