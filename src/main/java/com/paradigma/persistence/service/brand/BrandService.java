package com.paradigma.persistence.service.brand;

import java.util.List;

import com.paradigma.graphql.schema.car.brand.Brand;

/**
 * 
 * 
 * @author manuel
 *
 */
public interface BrandService {

	public Brand findById(String id);

	public List<Brand> findAll();

}
