package com.paradigma.persistence.service.car;

import java.util.List;

import com.paradigma.graphql.schema.car.car.Car;
import com.paradigma.persistence.model.CarMO;

/**
 * Operaciones sobre el coche
 * 
 * @author manuel
 *
 */
public interface CarService {

	public Car findById(String id);

	public List<Car> findAll();

}
