package com.paradigma.vehicles.persistence.service.brand.transformer;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import com.paradigma.graphql.schema.car.brand.Brand;
import com.paradigma.vehicles.persistence.model.BrandMO;

/**
 * Cast de objetos para el servicio de Brand
 * 
 * @author manuel
 *
 */
@Component
public class BrandTransformerImpl implements BrandTransformer {

	/**
	 * Transformamos al objeto de negocio
	 * 
	 * @param brandMO
	 * @return
	 */
	public Brand brandMOToBrand(BrandMO brandMO) {
		Validate.notNull(brandMO);
		return new Brand.Builder().withId(brandMO.getId()).withName(brandMO.getName()).build();
	}

}
