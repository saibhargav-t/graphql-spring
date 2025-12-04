package com.graphql.model;

import java.util.Map;

import lombok.Data;

@Data
public class QLRequestBody {

	private String query;
	private String operationName;
	private Map<String,Object> variables;
}
