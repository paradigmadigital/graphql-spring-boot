package com.paradigma.vehicles.persistence.service.brand.transformer;

import com.paradigma.graphql.schema.car.brand.Brand;
import com.paradigma.vehicles.persistence.model.BrandMO;

/**
 * Transformador de objetos entre capas
 * @author manuel
 *
 */
public interface BrandTransformer {

	public Brand brandMOToBrand(BrandMO brandMO);
}
