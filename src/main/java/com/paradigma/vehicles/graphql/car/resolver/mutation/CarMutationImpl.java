package com.paradigma.vehicles.graphql.car.resolver.mutation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paradigma.graphql.schema.car.car.Car;
import com.paradigma.graphql.schema.car.car.MutateCars;
import com.paradigma.vehicles.persistence.service.car.CarService;

/**
 * Implementación de las operaciones sobre la entidad Car
 * 
 * @author manuel
 *
 */
@Component
public class CarMutationImpl implements MutateCars {

	@Autowired
	CarService carService;

	@Override
	public Car createCar(CreateCarArgs args) {
		return carService.createCar(args.getCar());
	}

	@Override
	public String deleteCar(DeleteCarArgs args) {
		return carService.deleteCar(args.getId());
	}
}
