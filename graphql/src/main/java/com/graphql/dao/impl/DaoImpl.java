package com.graphql.dao.impl;

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;

import com.graphql.dao.DAO;
import com.graphql.model.Users;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
@Slf4j
public class DaoImpl implements DAO {

	private final R2dbcEntityTemplate template;

	@Override
	public Mono<Users> findUser(int id) {
		log.info("In findAll | DAO Impl");
		return template.select(Users.class).matching(Query.query(Criteria.where("id").is(id))).one();
	}

	@Override
	public Flux<Users> findAll() {
		return template.select(Users.class).all();
	}

}
