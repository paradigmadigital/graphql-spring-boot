package com.paradigma.graphql.cars.provider.brand.resolver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.paradigma.graphql.schema.car.brand.Brand;

/**
 * Resolver para la entidad Brand
 * 
 * @author manuel
 *
 */
@Component
public class BrandResolver implements Brand.Resolver {

	@Override
	public List<Brand> resolve(List<Brand> list) {

		return new ArrayList<Brand>();
	}

}
