package com.paradigma.persistence.service.model;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paradigma.graphql.schema.car.model.Model;
import com.paradigma.persistence.model.ModelMO;
import com.paradigma.persistence.repository.model.ModelRepository;
import com.paradigma.persistence.service.model.transformer.ModelTransformer;

/**
 * Service con todos los métodos referentes a la entidad Model
 * 
 * @author manuel
 *
 */
@Service
public class ModelServiceImpl implements ModelService {

	@Autowired
	ModelRepository modelRepository;

	@Autowired
	ModelTransformer modelTransformer;

	/**
	 * Obtenemos el elemento por el Id
	 * 
	 * @param id
	 * @return
	 */
	public Model findById(String id) {
		ModelMO modelMO = modelRepository.findOne(id);
		Model modelMOToModel = null;
		if (modelMO != null) {
			modelMOToModel = modelTransformer.modelMOToModel(modelMO);
		}
		return modelMOToModel;
	}

	/**
	 * Retornamos todos los datos sin filtros
	 * 
	 * @return
	 */
	public List<Model> findAll() {
		List<ModelMO> modelsMO = modelRepository.findAll();
		List<Model> models = modelsMO.stream().map(mapper -> modelTransformer.modelMOToModel(mapper))
				.collect(Collectors.toList());
		return models;
	}

}
