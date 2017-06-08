package com.paradigma.graphql.config;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.execution.batched.BatchedExecutionStrategy;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.schema.GraphQLType;

@RestController
@RequestMapping("/graphqlwwwwww")
public class GraphqlController {

	// private static final LOGGER LOGGER = LoggerFactory.getLogger(GraphQLServerController.class);

	public static final String DEFAULT_QUERY_KEY = "query";
	public static final String DEFAULT_VARIABLES_KEY = "variables";
	public static final String DEFAULT_OPERATION_NAME_KEY = "operationName";
	public static final String DEFAULT_DATA_KEY = "data";
	public static final String DEFAULT_FILENAME_UPLOAD_KEY = "file";
	public static final String DEFAULT_ERRORS_KEY = "errors";
	public static final String HEADER_SCHEMA_NAME = "graphql-schema";

	private ObjectMapper objectMapper = new ObjectMapper();

	// ---

	@Autowired
	Map<String, GraphQLType> types;

	/* Refactor */
	private GraphQL getSchema() {
		GraphQLObjectType queryType = (GraphQLObjectType) types.get("QueryCar");
		GraphQLObjectType mutationType = (GraphQLObjectType) types.get("MutateCars");

		GraphQLSchema schema = GraphQLSchema.newSchema().query(queryType).mutation(mutationType)
				.build(new HashSet<>(types.values()));
		GraphQL graphQL = new GraphQL(schema, new BatchedExecutionStrategy());
		return graphQL;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getGraphql(@RequestParam(DEFAULT_QUERY_KEY) String query) {

		GraphQL graphQL = getSchema();

		ExecutionResult result = graphQL.execute(query);
		// ExecutionResult result = graphQL.execute("{car{id}}");

		Map<String, Object> responseEntity = responseEntity(result);
		return ResponseEntity.ok(responseEntity);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Map<String, Object>> postGraphql(@RequestBody Map<String, Object> body,
			@RequestHeader(value = HEADER_SCHEMA_NAME, required = false) String graphQLSchemaName,
			HttpServletRequest httpServletRequest) {

		// TODO ver como se hace el tema de la operación y demás pasando más parámetros
		final String query = (String) body.get(DEFAULT_QUERY_KEY);

		GraphQL graphQL = getSchema();
		ExecutionResult result = graphQL.execute(query);
		// ExecutionResult result = graphQL.execute("{car{id}}");

		Map<String, Object> responseEntity = responseEntity(result);
		return ResponseEntity.ok(responseEntity);

	}

	/**
	 * Respondemos los datos después de la ejecución: errores o bien la información como se indica en la documentación.
	 * http://graphql.org/learn/serving-over-http/
	 * 
	 * @param executionResult
	 * @return
	 */
	private Map<String, Object> responseEntity(ExecutionResult executionResult) {
		/* Mapa con la respuesta */
		Map<String, Object> result = new LinkedHashMap<>();
		if (executionResult.getErrors().size() > 0) {
			result.put(DEFAULT_ERRORS_KEY, executionResult.getErrors());
		}
		/* Retornamos los datos devueltos */
		Object data = executionResult.getData() == null ? new HashMap<String, String>() : executionResult.getData();
		result.put(DEFAULT_DATA_KEY, data);
		return result;
	}

	// @RequestMapping(method = RequestMethod.POST, consumes = "application/graphql")
	// public ResponseEntity<Map<String, Object>> postGraphQL(@RequestBody String query,
	// @RequestParam(value = DEFAULT_OPERATION_NAME_KEY, required = false) String operationName,
	// @RequestHeader(value = HEADER_SCHEMA_NAME, required = false) String graphQLSchemaName,
	// HttpServletRequest httpServletRequest) {
	//
	// final GraphQLContext graphQLContext = new GraphQLContext();
	// graphQLContext.setHttpRequest(httpServletRequest);
	//
	// final Map<String, Object> result = evaluateAndBuildResponseMap(query, operationName, graphQLContext, new
	// HashMap<>(), graphQLSchemaName);
	// return ResponseEntity.ok(result);
	// }
	//
	// @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	// public ResponseEntity<Map<String, Object>> postJson(@RequestBody Map<String, Object> body,
	// @RequestHeader(value = HEADER_SCHEMA_NAME, required = false) String graphQLSchemaName,
	// HttpServletRequest httpServletRequest) {
	//
	// final String query = (String) body.get(getQueryKey());
	// final String operationName = (String) body.get(DEFAULT_OPERATION_NAME_KEY);
	//
	// Map<String, Object> variables = null;
	// Object variablesObject = body.get(getVariablesKey());
	// if (variablesObject != null && variablesObject instanceof Map)
	// variables = (Map<String, Object>) variablesObject;
	//
	// final GraphQLContext graphQLContext = new GraphQLContext();
	// graphQLContext.setHttpRequest(httpServletRequest);
	//
	// final Map<String, Object> result = evaluateAndBuildResponseMap(query, operationName, graphQLContext, variables,
	// graphQLSchemaName);
	//
	// return ResponseEntity.ok(result);
	// }
	//
	// @RequestMapping(method = RequestMethod.POST, consumes = "multipart/form-data")
	// public ResponseEntity<Map<String, Object>> uploadFile(@RequestParam(DEFAULT_FILENAME_UPLOAD_KEY) MultipartFile
	// file,
	// @RequestParam(DEFAULT_QUERY_KEY) String query,
	// @RequestParam(value = DEFAULT_VARIABLES_KEY, required = false) String variables,
	// @RequestParam(value = DEFAULT_OPERATION_NAME_KEY, required = false) String operationName,
	// @RequestHeader(value = HEADER_SCHEMA_NAME, required = false) String graphQLSchemaName,
	// HttpServletRequest httpServletRequest) throws IOException {
	//
	// final GraphQLContext graphQLContext = new GraphQLContext();
	// graphQLContext.setUploadedFile(file);
	// graphQLContext.setHttpRequest(httpServletRequest);
	//
	// final Map<String, Object> result = evaluateAndBuildResponseMap(query, operationName, graphQLContext,
	// decodeIntoMap(variables), graphQLSchemaName);
	// return ResponseEntity.ok(result);
	// }
	//
	// @RequestMapping(method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
	// public ResponseEntity<Map<String, Object>> uploadSmallFile(@RequestParam(DEFAULT_QUERY_KEY) String query,
	// @RequestParam(value = DEFAULT_VARIABLES_KEY, required = false) String variables,
	// @RequestParam(value = DEFAULT_OPERATION_NAME_KEY, required = false) String operationName,
	// @RequestHeader(value = HEADER_SCHEMA_NAME, required = false) String graphQLSchemaName,
	// HttpServletRequest httpServletRequest) throws IOException {
	//
	// final GraphQLContext graphQLContext = new GraphQLContext();
	// graphQLContext.setHttpRequest(httpServletRequest);
	//
	// final Map<String, Object> result = evaluateAndBuildResponseMap(query, operationName, graphQLContext,
	// decodeIntoMap(variables), graphQLSchemaName);
	// return ResponseEntity.ok(result);
	// }
	//
	// private Map<String, Object> decodeIntoMap(final String variablesParam) throws IOException {
	// return objectMapper.readValue(variablesParam, Map.class);
	// }
	//
	// private Map<String, Object> evaluateAndBuildResponseMap(final String query,
	// final String operationName,
	// final GraphQLContext graphQLContext,
	// final Map<String, Object> variables,
	// final String graphQLSchemaName) {
	// final Map<String, Object> result = new LinkedHashMap<>();
	// final GraphQLSchemaHolder graphQLSchemaHolder = getGraphQLSchemaContainer(graphQLSchemaName);
	// final ExecutionResult executionResult = evaluate(query, operationName, graphQLContext, variables,
	// graphQLSchemaHolder);
	//
	// if (executionResult.getErrors().size() > 0) {
	// result.put(DEFAULT_ERRORS_KEY, executionResult.getErrors());
	// LOGGER.error("Errors: {}", executionResult.getErrors());
	// }
	//
	// result.put(DEFAULT_DATA_KEY, executionResult.getData());
	// return result;
	// }
	//
	// private ExecutionResult evaluate(final String query,
	// final String operationName,
	// final GraphQLContext graphQLContext,
	// final Map<String, Object> variables,
	// final GraphQLSchemaHolder graphQLSchemaHolder) {
	// ExecutionResult executionResult;
	//
	// if (graphQLSchemaHolder == null) {
	// executionResult = new ExecutionResultImpl(Lists.newArrayList(new ErrorGraphQLSchemaUndefined()));
	// } else {
	// try {
	// GraphQLQueryExecutor graphQLQueryExecutor = GraphQLQueryExecutor.create(graphQLSchemaHolder)
	// .query(query).context(graphQLContext);
	//
	// if (variables != null)
	// graphQLQueryExecutor.arguments(variables);
	//
	// if (StringUtils.hasText(operationName))
	// graphQLQueryExecutor.operation(operationName);
	//
	// executionResult = graphQLQueryExecutor.execute();
	//
	// } catch (Exception e) {
	// LOGGER.error("Error occurred evaluating query: {}", query);
	// executionResult = new ExecutionResultImpl(Lists.newArrayList(new ErrorGraphQLQueryEvaluation()));
	// }
	// }
	//
	// return executionResult;
	// }
	//
	// private String getQueryKey() {
	// return StringUtils.hasText(graphQLProperties.getServer().getQueryKey()) ?
	// graphQLProperties.getServer().getQueryKey() : DEFAULT_QUERY_KEY;
	// }
	//
	// private String getVariablesKey() {
	// return StringUtils.hasText(graphQLProperties.getServer().getVariablesKey()) ?
	// graphQLProperties.getServer().getVariablesKey() : DEFAULT_VARIABLES_KEY;
	// }
	//
	// public GraphQLSchemaHolder getGraphQLSchemaContainer(String graphQLSchema) {
	// if (StringUtils.hasText(graphQLSchema))
	// return graphQLSchemaLocator.getGraphQLSchemaHolder(graphQLSchema);
	// else if (graphQLSchemaLocator.getTotalNumberOfSchemas() == 1)
	// return graphQLSchemaLocator.getSingleSchema();
	//
	// return null;
	// }
}
