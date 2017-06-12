package com.paradigma.vehicles.persistence.service.model.transformer;

import com.paradigma.graphql.schema.car.model.Model;
import com.paradigma.vehicles.persistence.model.ModelMO;

public interface ModelTransformer {

	/**
	 * Transformamos al objeto de negocio
	 * 
	 * @param modelMO
	 * @return
	 */
	Model modelMOToModel(ModelMO modelMO);

}