package com.graphql.service.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.graphql.dao.DAO;
import com.graphql.model.Users;
import com.graphql.service.Services;

import graphql.schema.DataFetcher;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicesImpl implements Services {

	private final DAO dao;

	@Override
	public DataFetcher<CompletableFuture<Users>> getUser() {
		return env -> {
			int userId = env.getArgument("id");
			return dao.findUser(userId).toFuture();
		};
	}

	@Override
	public DataFetcher<CompletableFuture<List<Users>>> getAll() {
		return env -> dao.findAll().collectList().toFuture();
	}

}
