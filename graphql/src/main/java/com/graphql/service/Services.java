package com.graphql.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.graphql.model.Users;

import graphql.schema.DataFetcher;

public interface Services {
	
	public DataFetcher<CompletableFuture<Users>> getUser();
	public DataFetcher<CompletableFuture<List<Users>>> getAll();
	public DataFetcher<CompletableFuture<String>> saveUser();
}
