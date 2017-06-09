package com.paradigma.graphql.cars.provider.car.resolver;

import java.util.List;

import org.springframework.stereotype.Component;

import com.paradigma.graphql.schema.car.car.Car;

/**
 * Resolver para la entidad Car
 * 
 * @author manuel
 *
 */
@Component
public class CarResolver implements Car.Resolver {

	@Override
	public List<Car> resolve(List<Car> list) {

		return list;
	}

}
