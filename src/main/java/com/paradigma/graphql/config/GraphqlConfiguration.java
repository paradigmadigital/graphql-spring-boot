package com.paradigma.graphql.config;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Provider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import graphql.schema.GraphQLType;

@EnableMongoRepositories(basePackages = "com.paradigma")
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
		Map<String, GraphQLType> types = typeList.stream().map(Provider::get)
				.collect(Collectors.toMap(GraphQLType::getName, Function.identity()));
		return types;
	}

}
