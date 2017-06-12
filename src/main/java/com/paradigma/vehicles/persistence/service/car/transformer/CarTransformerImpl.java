package com.paradigma.vehicles.persistence.service.car.transformer;

import java.util.Optional;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import com.paradigma.graphql.schema.car.car.Car;
import com.paradigma.graphql.schema.car.model.Model;
import com.paradigma.vehicles.persistence.model.CarMO;
import com.paradigma.vehicles.persistence.model.ModelMO;

/**
 * Cast de objetos para el servicio de Car
 * 
 * @author manuel
 *
 */
@Component
public class CarTransformerImpl implements CarTransformer {

	
	/* (non-Javadoc)
	 * @see com.paradigma.persistence.service.car.transformer.CarTransformer#carMOToCar(com.paradigma.persistence.model.CarMO)
	 */
	@Override
	public Car carMOToCar(CarMO carMO) {
		Validate.notNull(carMO);
		// No retornaremos el modelo para hacer uso de la independencia y para hacerlo como ejemplo de recuperación del mismo
		Optional<ModelMO> model =  Optional.of(carMO.getModel());
		Model.Unresolved unresolved = new Model.Unresolved(model.orElse(new ModelMO()).getId())  ;
		return new Car.Builder().withColor(carMO.getColor()).withModel(unresolved).withId(carMO.getId()).build();
	}

}
