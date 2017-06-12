package com.paradigma.graphql.cars.provider.model.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paradigma.graphql.cars.provider.base.BaseResolver;
import com.paradigma.graphql.schema.car.model.Model;
import com.paradigma.persistence.service.model.ModelService;

/**
 * Resolver para la entidad Model
 * 
 * @author manuel
 *
 */
@Component
public class ModelResolver extends BaseResolver<Model> implements Model.Resolver {

	@Autowired
	ModelService modelService;

	@Override
	protected Model findById(Model unresolved) {
		return modelService.findById(unresolved.getId());
	}

	@Override
	protected Class<?> unresolvedClass() {
		return Model.Unresolved.class;
	}

}
