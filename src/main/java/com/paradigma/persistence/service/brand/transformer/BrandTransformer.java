package com.paradigma.persistence.service.brand.transformer;

import com.paradigma.graphql.schema.car.brand.Brand;
import com.paradigma.persistence.model.BrandMO;

/**
 * Transformador de objetos entre capas
 * @author manuel
 *
 */
public interface BrandTransformer {

	public Brand brandMOToBrand(BrandMO brandMO);
}
