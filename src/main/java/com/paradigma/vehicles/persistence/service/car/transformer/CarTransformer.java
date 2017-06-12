package com.paradigma.vehicles.persistence.service.car.transformer;

import com.paradigma.graphql.schema.car.car.Car;
import com.paradigma.vehicles.persistence.model.CarMO;

public interface CarTransformer {

	/**
	 * Transformamos al objeto de negocio
	 * 
	 * @param carMO
	 * @return
	 */
	Car carMOToCar(CarMO carMO);

}