package com.paradigma.graphql.cars.provider.model.resolver;

import java.util.List;

import org.springframework.stereotype.Component;

import com.paradigma.graphql.schema.car.model.Model;

/**
 * Resolver para la entidad Model
 * 
 * @author manuel
 *
 */
@Component
public class ModelResolver implements Model.Resolver {

	@Override
	public List<Model> resolve(List<Model> list) {

		return list;
	}

}
