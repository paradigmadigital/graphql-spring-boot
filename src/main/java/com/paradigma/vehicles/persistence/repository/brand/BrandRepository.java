package com.paradigma.vehicles.persistence.repository.brand;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.paradigma.vehicles.persistence.model.BrandMO;

/**
 * Repositorio de acceso para las marcas
 * 
 * @author manuel
 *
 */
public interface BrandRepository extends MongoRepository<BrandMO, String> {

}
