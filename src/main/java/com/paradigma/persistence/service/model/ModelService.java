package com.paradigma.persistence.service.model;

import java.util.List;

import com.paradigma.graphql.schema.car.model.Model;

/**
 * 
 * 
 * @author manuel
 *
 */
public interface ModelService {

	public Model findById(String id);

	public List<Model> findAll();

}
