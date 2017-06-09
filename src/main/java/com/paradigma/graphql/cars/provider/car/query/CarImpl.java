package com.paradigma.graphql.cars.provider.car.query;

import org.springframework.stereotype.Component;

import com.paradigma.graphql.schema.car.car.Car;
import com.paradigma.graphql.schema.car.model.Model;

/**
 * Implementamos la obtención de la entidad Car con cada uno de sus atributos
 * 
 * @author manuel
 *
 */
@Component
public class CarImpl implements Car {

	@Override
	public String getColor() {
		
		return Car.super.getColor();
	}

	@Override
	public String getId() {
		
		return Car.super.getId();
	}

	@Override
	public Model getModel() {
		
		return Car.super.getModel();
	}

}
