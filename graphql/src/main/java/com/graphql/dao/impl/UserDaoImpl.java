package com.graphql.dao.impl;

import java.util.UUID;

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;

import com.graphql.dao.UserDAO;
import com.graphql.model.Users;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserDaoImpl implements UserDAO {

	private final R2dbcEntityTemplate template;

	@Override
	public Mono<Users> findUser(UUID id) {
		log.info("In findAll | DAO Impl");
		return template.select(Users.class).matching(Query.query(Criteria.where("id").is(id))).one();
	}

	@Override
	public Flux<Users> findAll() {
		return template.select(Users.class).all();
	}

	@Override
	public Mono<UUID> saveUser(Users users) {
		UUID userId = UUID.randomUUID();
		users.setId(userId);
		return template.insert(Users.class).using(users).then().thenReturn(userId);
	}

	

}
