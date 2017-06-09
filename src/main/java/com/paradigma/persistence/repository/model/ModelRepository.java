package com.paradigma.persistence.repository.model;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.paradigma.persistence.model.ModelMO;

/**
 * Repositorio de acceso para los modelos
 * 
 * @author manuel
 *
 */
public interface ModelRepository extends MongoRepository<ModelMO, String> {

}
