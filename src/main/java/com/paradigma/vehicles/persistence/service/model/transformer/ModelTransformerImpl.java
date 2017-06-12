package com.paradigma.vehicles.persistence.service.model.transformer;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paradigma.graphql.schema.car.brand.Brand;
import com.paradigma.graphql.schema.car.model.Model;
import com.paradigma.vehicles.persistence.model.ModelMO;
import com.paradigma.vehicles.persistence.service.brand.transformer.BrandTransformer;

/**
 * Cast de objetos para el servicio de Model
 * 
 * @author manuel
 *
 */
@Component
public class ModelTransformerImpl implements ModelTransformer {
	
	@Autowired
	BrandTransformer brandTransformer;
	
	/* (non-Javadoc)
	 * @see com.paradigma.persistence.service.model.transformer.ModelTransformer#modelMOToModel(com.paradigma.persistence.model.ModelMO)
	 */
	@Override
	public Model modelMOToModel(ModelMO modelMO) {
		Validate.notNull(modelMO);
		Brand brand = brandTransformer.brandMOToBrand(modelMO.getBrand());
		return new Model.Builder().withId(modelMO.getId()).withName(modelMO.getName()).withYear(modelMO.getYear())
				.withBrand(brand).build();
	}

}
