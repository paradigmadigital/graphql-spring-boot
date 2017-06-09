package com.paradigma.graphql.cars.provider.model.query;

import org.springframework.stereotype.Component;

import com.paradigma.graphql.schema.car.brand.Brand;
import com.paradigma.graphql.schema.car.model.Model;

/**
 * Implementamos la obtención de la entidad model con cada uno de sus atributos
 * 
 * @author manuel
 *
 */
@Component
public class ModelImpl implements Model {

	@Override
	public String getName() {
		return "pepito";
	}
	
	
	@Override
	public Brand getBrand() {
		
		return Model.super.getBrand();
	}
	
	@Override
	public String getId() {
		
		return Model.super.getId();
	}
	
	@Override
	public Integer getYear() {
		
		return Model.super.getYear();
	}
	
	
	
	
	
	
}
