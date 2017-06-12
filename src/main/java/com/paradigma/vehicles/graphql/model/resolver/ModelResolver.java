package com.paradigma.vehicles.graphql.model.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paradigma.graphql.schema.car.model.Model;
import com.paradigma.vehicles.graphql.base.BaseResolver;
import com.paradigma.vehicles.persistence.service.model.ModelService;

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
