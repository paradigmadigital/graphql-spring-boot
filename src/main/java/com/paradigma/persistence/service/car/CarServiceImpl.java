package com.paradigma.persistence.service.car;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paradigma.graphql.schema.car.car.Car;
import com.paradigma.persistence.model.CarMO;
import com.paradigma.persistence.repository.car.CarRepository;

/**
 * Service con todos los métodos referentes a la entidad Car
 * 
 * @author manuel
 *
 */
@Service
public class CarServiceImpl implements CarService {

	@Autowired
	CarRepository carRepository;

	/**
	 * Obtenemos el elemento por el Id
	 * @param id
	 * @return
	 */
	public Car findById(String id) {
		CarMO carMO = carRepository.findOne(id);
		return carMOToCar(carMO);
	}

	
	
	/**
	 * Retornamos todos los datos sin filtros 
	 * 
	 * @return
	 */
	public List<Car> findAll() {
		List<CarMO> carsMO = carRepository.findAll();
		List<Car> cars = new ArrayList<>();
		for (CarMO carMO : carsMO) {
			cars.add(carMOToCar(carMO));
		}
		return cars;
	}

	
	/**
	 * Transformamos al objeto de negocio 
	 * 
	 * @param carMO
	 * @return
	 */
	public Car carMOToCar(CarMO carMO) {
		Validate.notNull(carMO);
		return new Car.Builder().withColor(carMO.getColor()).withModel(null).withId(carMO.getId()).build();
	}

	
/*
	
	public Car createCar(InputCreateCar input) {
		CarMO mo = new CarMO();
		mo.setColor(color);
		//mo.setModel(model);
		
		CarMO carMO = carRepository.findOne(id);
		return carMOToCar(carMO);
	}
*/	
	
}
