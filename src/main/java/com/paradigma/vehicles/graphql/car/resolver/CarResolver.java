package com.paradigma.vehicles.graphql.car.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paradigma.graphql.schema.car.car.Car;
import com.paradigma.vehicles.graphql.base.BaseResolver;
import com.paradigma.vehicles.persistence.service.car.CarService;

/**
 * Resolver para la entidad Car
 * 
 * @author manuel
 *
 */
@Component
public class CarResolver extends BaseResolver<Car> implements Car.Resolver {

	@Autowired
	CarService carService;

	@Override
	protected Car findById(Car unresolver) {
		Car findCar = carService.findById(unresolver.getId());
		return findCar;
	}

	@Override
	protected Class<?> unresolvedClass() {
		return Car.Unresolved.class;
	}

}
