package com.graphql.dao;

import com.graphql.model.Users;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DAO {

	public Mono<Users> findUser(int id);
	public Flux<Users> findAll();
}
