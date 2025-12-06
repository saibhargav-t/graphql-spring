package com.graphql.dao;

import java.util.UUID;

import com.graphql.model.Users;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserDAO {

	public Mono<Users> findUser(UUID id);

	public Flux<Users> findAll();

	Mono<UUID> saveUser(Users users);
}
