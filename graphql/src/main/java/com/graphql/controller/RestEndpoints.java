package com.graphql.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graphql.model.QLRequestBody;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/graphql/v1")
@RequiredArgsConstructor
@Slf4j
public class RestEndpoints {

	private final GraphQL graphQL;

	// http://localhost:2619/graphql/v1/users
	@PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ExecutionResult> execute(@RequestBody QLRequestBody body) {
		log.info("In execute() | RestEndpoints.class");
		return Mono.fromCompletionStage(graphQL.executeAsync(ExecutionInput.newExecutionInput().query(body.getQuery())
				.operationName(body.getOperationName()).variables(body.getVariables()).build()));
	}
}
