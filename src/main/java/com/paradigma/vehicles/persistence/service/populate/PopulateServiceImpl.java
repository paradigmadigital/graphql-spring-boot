package com.paradigma.vehicles.persistence.service.populate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.paradigma.vehicles.persistence.model.BrandMO;
import com.paradigma.vehicles.persistence.model.CarMO;
import com.paradigma.vehicles.persistence.model.ModelMO;

/**
 * Service con todos los métodos referentes a la entidad Car
 * 
 * @author manuel
 *
 */
@Service
public class PopulateServiceImpl implements PopulateService {

	@Autowired
	MongoTemplate mongoTemplate;

	
	/**
	 * Generamos datos de prueba 
	 */
	@PostConstruct
	public void databasePopulate() {
		/* Borramos los datos si existieran */
		mongoTemplate.dropCollection(BrandMO.class);
		mongoTemplate.dropCollection(ModelMO.class);
		mongoTemplate.dropCollection(CarMO.class);
		
		
		/* Creamos una marca */
		BrandMO brandMO = new BrandMO();
		brandMO.setName("Seat");
		mongoTemplate.save(brandMO);
		
		/* Creamos un modelo */
		ModelMO modelMO = new ModelMO();
		modelMO.setBrand(brandMO);
		modelMO.setName("Ibiza");
		modelMO.setYear(2011);
		
		mongoTemplate.save(modelMO);
		
		/* Creamos un coche */
		CarMO carMO = new CarMO();
		carMO.setColor("Rojo");
		carMO.setModel(modelMO);
		
		mongoTemplate.save(carMO);
		
	}
	
}
