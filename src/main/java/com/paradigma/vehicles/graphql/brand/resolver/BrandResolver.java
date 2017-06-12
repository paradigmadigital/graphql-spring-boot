package com.paradigma.vehicles.graphql.brand.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paradigma.graphql.schema.car.brand.Brand;
import com.paradigma.vehicles.graphql.base.BaseResolver;
import com.paradigma.vehicles.persistence.service.brand.BrandService;

/**
 * Resolver para la entidad Brand
 * 
 * @author manuel
 *
 */
@Component
public class BrandResolver extends BaseResolver<Brand> implements Brand.Resolver {

	@Autowired
	BrandService bradService;

	@Override
	protected Brand findById(Brand unresolved) {
		return bradService.findById(unresolved.getId());
	}

	@Override
	protected Class<?> unresolvedClass() {
		return Brand.Unresolved.class;
	}
}
