package com.graphql.dao.impl;

import java.util.UUID;

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;

import com.graphql.dao.AddressRepository;
import com.graphql.model.Address;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Repository
public class AddressDAOImpl implements AddressRepository {

	private final R2dbcEntityTemplate template;

	@Override
	public Mono<UUID> createAddress(Address address) {
		UUID addressId = UUID.randomUUID();
		address.setId(addressId);
		return template.insert(Address.class).using(address).then().thenReturn(addressId);
	}

	@Override
	public Mono<Address> getAddress(UUID userId) {
		return template.select(Address.class).matching(Query.query(Criteria.where("USER_ID").is(userId))).one();
	}

}
