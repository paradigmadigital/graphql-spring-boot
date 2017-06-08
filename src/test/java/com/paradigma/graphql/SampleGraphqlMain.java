package com.paradigma.graphql;

import graphql.GraphQL;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import java.util.Map;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

//https://github.com/merapar/spring-boot-starter-graphql
public class SampleGraphqlMain {

	public static void main(String[] args) {
		GraphQLObjectType queryType = newObject().name("helloWorldQuery")
				.field(newFieldDefinition().type(GraphQLString).name("hello").staticValue("world")).build();

		GraphQLSchema schema = GraphQLSchema.newSchema().query(queryType).build();
		Map<String, Object> result = (Map<String, Object>) new GraphQL(schema).execute("{hello}").getData();

		System.out.println(result);
	}
}