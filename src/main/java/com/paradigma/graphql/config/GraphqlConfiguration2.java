package com.paradigma.graphql.config;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import graphql.GraphQL;
import graphql.execution.batched.BatchedExecutionStrategy;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.schema.GraphQLType;
import graphql.servlet.GraphQLSchemaProvider;

@ComponentScan("com.paradigma")
@Configuration
public class GraphqlConfiguration2 {

	/**
	 * Mapa con los tipos disponibles para el schema
	 * 
	 * @param typeList
	 * @return
	 */
	@Autowired
	Map<String, GraphQLType> types;

	@Bean
	public graphql.schema.GraphQLSchema graphQLSchemaLocator() throws ClassNotFoundException {

		return getSchema();
	}

	/* Refactor */
	private GraphQLSchema getSchema() {
		GraphQLObjectType queryType = (GraphQLObjectType) types.get("QueryCar");
		GraphQLObjectType mutationType = (GraphQLObjectType) types.get("MutateCars");

//		List<GraphQLType> onlyObjectTypes = types.values().stream().filter(f -> f instanceof GraphQLObjectType)
//				.collect(Collectors.toList());

		GraphQLSchema schema = GraphQLSchema.newSchema().query(queryType).mutation(mutationType)
				.build(new HashSet<>(types.values()));
		return schema;
	}

	// /** Configuraciones propias del plugin de Spring Boot */
	// @Bean
	// public GraphQLSchemaBeanFactory graphQLSchemaBeanFactory() {
	// return new SpringGraphQLSchemaBeanFactory();
	// }
	//
	// // configuration can be customized depending on the case
	// @Bean
	// public GraphQLSchemaConfig graphQLSchemaConfig() {
	// GraphQLSchemaConfig graphQLSchemaConfig = new GraphQLSchemaConfig();
	// return graphQLSchemaConfig;
	// }
	//
	// @Bean
	// public GraphQLSchemaBuilder graphQLSchemaBuilder() {
	// return new GraphQLSchemaBuilder(graphQLSchemaConfig(), graphQLSchemaBeanFactory());
	// }
	//
	// // use as is
	// @Bean
	// public GraphQLSchemaHolder graphQLSchemaHolder() {
	// return graphQLSchemaBuilder().buildSchema(TodoSchema.class);
	// }
	//
	//

	// /* Refactor */
	// private GraphQL getSchema(List<Provider<? extends GraphQLType>> typeList) {
	// GraphQLObjectType queryType = (GraphQLObjectType) types.get("QueryCar");
	// GraphQLObjectType mutationType = (GraphQLObjectType) types.get("MutateCars");
	//
	// GraphQLSchema schema = GraphQLSchema.newSchema().query(queryType).mutation(mutationType)
	// .build(new HashSet<>(types.values()));
	// GraphQL graphQL = new GraphQL(schema, new BatchedExecutionStrategy());
	// return graphQL;
	// }

}
