package com.paradigma.persistence.repository.brand;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.paradigma.persistence.model.BrandMO;

/**
 * Repositorio de acceso para las marcas
 * 
 * @author manuel
 *
 */
public interface BrandRepository extends MongoRepository<BrandMO, String> {

}
