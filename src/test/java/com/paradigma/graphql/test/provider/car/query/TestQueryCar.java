package com.paradigma.graphql.test.provider.car.query;

import javax.annotation.PostConstruct;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.paradigma.vehicles.config.GraphqlConfiguration;
import com.paradigma.vehicles.persistence.model.BrandMO;
import com.paradigma.vehicles.persistence.model.CarMO;
import com.paradigma.vehicles.persistence.model.ModelMO;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;

/**
 * Test asociados a la consulta de la query específica de nuestro graphql
 * 
 * @author manuel
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { GraphqlConfiguration.class, GraphqlConfiguration.class })
@Profile("test")
public class TestQueryCar {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestQueryCar.class);

	@Autowired
	GraphQLSchema graphQLSchema;
	
	GraphQL graphQL;

	@Autowired
	MongoTemplate mongoTemplate;
	
	
	public TestQueryCar() {

	}

	@PostConstruct
	public void createGraphQL() {
		graphQL = new GraphQL.Builder(graphQLSchema).build();
	}

	@Before
	public void prepareTest() {
		/*  Populamos la base de datos en cada uno de los test ???? */
		LOGGER.debug("Preparando ejecución");
		databasePopulate();
	}

	@Test
	public void testQuery() throws Exception {

		// Consultas varias
		ExecutionResult result = graphQL.execute("{cars{id}}");
		checkExecution(result);

		result = graphQL.execute("{cars{id color model {id name}}}");
		checkExecution(result);

		result = graphQL.execute("{cars{id color model {id name brand { id name }}}}");
		checkExecution(result);

	}

	// @Test
	public void testMutation() throws Exception {
		// Creamos un Car
		ExecutionResult result = graphQL
				.execute("mutation{createCar(car:{color:\"Green\" , modelId:\"593ebb10674d4c0bef6c4c2a\"}){id}}");
		checkExecution(result);

		// Eliminamos el coche creado
		result = graphQL.execute("mutation{delete(id: \"adasd\"){}}");
		checkExecution(result);
	}

	/**
	 * Validamos si en la ejecución hemos recibido algún error
	 * 
	 * @param result
	 * @throws Exception
	 */
	private void checkExecution(ExecutionResult result) throws Exception {
		if (null == result.getErrors() || result.getErrors().size() <= 0)
			return;
		ObjectMapper om = new ObjectMapper();
		om.enable(SerializationFeature.INDENT_OUTPUT);
		String errors = om.writeValueAsString(result.getErrors());
		Assert.fail(errors);
	}

	
	/**
	 * generamos datos de prueba 
	 */
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
