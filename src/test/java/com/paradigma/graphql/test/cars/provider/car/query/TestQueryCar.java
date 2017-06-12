package com.paradigma.graphql.test.cars.provider.car.query;

import java.util.HashSet;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.paradigma.vehicles.config.GraphqlConfiguration;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.execution.batched.BatchedExecutionStrategy;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.schema.GraphQLType;
/**
 * Test asociados a la consulta de la query específica de los cars
 * 
 * @author manuel
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = GraphqlConfiguration.class)
public class TestQueryCar {

	@Autowired
	Map<String, GraphQLType> types;

	public GraphQLSchema createSchema() throws Exception {
		GraphQLObjectType queryType = (GraphQLObjectType) types.get("QueryCar");
		GraphQLObjectType mutationType = (GraphQLObjectType) types.get("MutateCars");
		GraphQLSchema schema = GraphQLSchema.newSchema().query(queryType)
				.mutation(mutationType).build(new HashSet<>(types.values()));
		return schema;
	}

	@Test
	public void testQuery() throws Exception {
		// Injector injector = setup();

		// TODO: Support "schema" type so this is generated too :)
		// Map<String, GraphQLType> types =
		// injector.getInstance(Key.get(new TypeLiteral<Map<String, GraphQLType>>(){}));

		GraphQLSchema schema = createSchema();

		GraphQL graphQL = new GraphQL(schema, new BatchedExecutionStrategy());
		ObjectMapper om = new ObjectMapper();
		om.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);

		// Using GraphQL Query:
		ExecutionResult result = graphQL.execute("{cars{id color model{id name}}}");
		checkExecutionResult(result);

		// // Using GraphQL Mutation:
		// ExecutionResult result = graphQL.execute(
		// "mutation{createPost(post:{title:\"NEW\" authorId:1}){title}}",
		// "authorized-user");
		// checkExecutionResult(result);
		// assertEquals("{\"createPost\":{\"title\":\"NEW\"}}", om.writeValueAsString(result.getData()));
		//
		// // ...or the API:
		// MutatePosts mutatePosts = injector.getInstance(MutatePosts.class);
		// mutatePosts.createPost(new MutatePosts.CreatePostArgs() {
		// public InputPost getPost() {
		// return new InputPost.Builder()
		// .withTitle("API")
		// .withAuthorId(2)
		// .build();
		// }
		// });
		//
		// // Using GraphQL Query:
		// result = graphQL.execute("{posts{title author{firstName lastName}}}");
		// checkExecutionResult(result);
		//
		// String value = om.writeValueAsString(result.getData());
		// assertEquals("{\"posts\":[{\"author\":{\"firstName\":\"Brian\",\"lastName\":\"Maher\"},\"title\":\"GraphQL
		// Rocks\"},{\"author\":{\"firstName\":\"Rahul\",\"lastName\":\"Singh\"},\"title\":\"Announcing
		// Callisto\"},{\"author\":{\"firstName\":\"Rahul\",\"lastName\":\"Singh\"},\"title\":\"Distelli Contributing to
		// Open
		// Source\"},{\"author\":{\"firstName\":\"Brian\",\"lastName\":\"Maher\"},\"title\":\"NEW\"},{\"author\":{\"firstName\":\"Rahul\",\"lastName\":\"Singh\"},\"title\":\"API\"}]}",
		// value);
		//
		// // ...or the API:
		// QueryPosts queryPosts = injector.getInstance(QueryPosts.class);
		// List<Post> posts = queryPosts.getPosts();
		// // ...since we are not using GraphQL, the authors will not be resolved:
		// assertEquals(posts.get(0).getAuthor().getClass(), Author.Unresolved.class);
		// assertArrayEquals(
		// new String[]{"GraphQL Rocks", "Announcing Callisto", "Distelli Contributing to Open Source", "NEW", "API"},
		// posts.stream().map((post) -> post.getTitle()).toArray(size -> new String[size]));
		// assertArrayEquals(
		// new Integer[]{1,2,2,1,2},
		// posts.stream().map((post) -> post.getAuthor().getId()).toArray(size -> new Integer[size]));
	}

	private void checkExecutionResult(ExecutionResult result) throws Exception {
		if (null == result.getErrors() || result.getErrors().size() <= 0)
			return;
		ObjectMapper om = new ObjectMapper();
		om.enable(SerializationFeature.INDENT_OUTPUT);
		String errors = om.writeValueAsString(result.getErrors());
		Assert.fail(errors);
	}

}
