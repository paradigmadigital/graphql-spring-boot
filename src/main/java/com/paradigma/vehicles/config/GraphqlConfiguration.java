package com.paradigma.vehicles.config;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Provider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.schema.GraphQLType;

@EnableMongoRepositories(basePackages = "com.paradigma")
@ComponentScan("com.paradigma")
@Configuration
public class GraphqlConfiguration {

	/** Nombre de la Root Query para exponerlo */
	private static final String QUERY_ROOT = "QueryRoot";

	/** Nombre de la mutación de los coches */
	private static final String CARS_MUTATION = "MutateCars";

	/**
	 * Mapa con los tipos disponibles para el schema
	 * 
	 * @param typeList
	 * @return
	 */
	@Bean
	public Map<String, GraphQLType> graphqlTypeMap(List<Provider<? extends GraphQLType>> typeList) {
		Map<String, GraphQLType> types = getGraphqlTypes(typeList);
		return types;
	}

	/**
	 * Integración con el SpringBoot Starter para Graphql, necesita la localización del esquema ya que no se va a usar
	 * las anotaciones para las declaraciones
	 * 
	 * @param typeList
	 * @return
	 * @throws ClassNotFoundException
	 */
	@Bean
	public graphql.schema.GraphQLSchema graphQLSchemaLocator(List<Provider<? extends GraphQLType>> typeList)
			throws ClassNotFoundException {
		return generateSchema(typeList);
	}

	/**
	 * Generamos el schema del Graphql
	 * 
	 * @param typeList
	 * @return
	 */
	private GraphQLSchema generateSchema(List<Provider<? extends GraphQLType>> typeList) {
		Map<String, GraphQLType> types = getGraphqlTypes(typeList);
		GraphQLObjectType queryType = (GraphQLObjectType) types.get(QUERY_ROOT);
		GraphQLObjectType mutationType = (GraphQLObjectType) types.get(CARS_MUTATION);
		GraphQLSchema schema = GraphQLSchema.newSchema().query(queryType).mutation(mutationType)
				.build(new HashSet<>(types.values()));
		return schema;
	}

	/**
	 * Obtenemos los tipos componentes de nuestro esquema a modo de map para localizar los elementos que deseemos
	 * 
	 * @param typeList
	 * @return
	 */
	private Map<String, GraphQLType> getGraphqlTypes(List<Provider<? extends GraphQLType>> typeList) {
		Map<String, GraphQLType> types = typeList.stream().map(Provider::get)
				.collect(Collectors.toMap(GraphQLType::getName, Function.identity()));
		return types;
	}

}
