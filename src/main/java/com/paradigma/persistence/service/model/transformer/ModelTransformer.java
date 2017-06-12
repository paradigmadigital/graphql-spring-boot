package com.paradigma.persistence.service.model.transformer;

import com.paradigma.graphql.schema.car.model.Model;
import com.paradigma.persistence.model.ModelMO;

public interface ModelTransformer {

	/**
	 * Transformamos al objeto de negocio
	 * 
	 * @param modelMO
	 * @return
	 */
	Model modelMOToModel(ModelMO modelMO);

}