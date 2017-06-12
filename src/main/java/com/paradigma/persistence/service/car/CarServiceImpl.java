package com.paradigma.persistence.service.car;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paradigma.graphql.schema.car.car.Car;
import com.paradigma.persistence.model.CarMO;
import com.paradigma.persistence.repository.car.CarRepository;
import com.paradigma.persistence.service.car.transformer.CarTransformer;

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
	
	@Autowired
	CarTransformer carTransformer;
	
	/**
	 * Obtenemos el elemento por el Id
	 * @param id
	 * @return
	 */
	public Car findById(String id) {
		CarMO carMO = carRepository.findOne(id);
		Car carMOToCar = null;
		if (carMO != null){
			carMOToCar = carTransformer.carMOToCar(carMO);
		}
		return carMOToCar;
	}

	
	
	/**
	 * Retornamos todos los datos sin filtros 
	 * 
	 * @return
	 */
	public List<Car> findAll() {
		List<CarMO> carsMO = carRepository.findAll();
		List<Car> cars = carsMO.stream().map(mapper -> carTransformer.carMOToCar(mapper)).collect(Collectors.toList());
		return cars;
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
