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
public class GraphqlConfiguration {

	/**
	 * Mapa con los tipos disponibles para el schema
	 * 
	 * @param typeList
	 * @return
	 */
	
	@Bean
	public Map<String, GraphQLType> graphqlTypeMap(List<Provider<? extends GraphQLType>> typeList) {
		Map<String, GraphQLType> types =  typeList.stream().map(Provider::get)
				.collect(Collectors.toMap(GraphQLType::getName, Function.identity()));
		return types;
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

	
//	/* Refactor */
//	private GraphQL getSchema(List<Provider<? extends GraphQLType>> typeList) {
//		GraphQLObjectType queryType = (GraphQLObjectType) types.get("QueryCar");
//		GraphQLObjectType mutationType = (GraphQLObjectType) types.get("MutateCars");
//
//		GraphQLSchema schema = GraphQLSchema.newSchema().query(queryType).mutation(mutationType)
//				.build(new HashSet<>(types.values()));
//		GraphQL graphQL = new GraphQL(schema, new BatchedExecutionStrategy());
//		return graphQL;
//	}
	

}
