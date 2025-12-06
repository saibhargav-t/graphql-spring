package com.graphql.dao;

import java.util.UUID;

import com.graphql.model.Address;

import reactor.core.publisher.Mono;

public interface AddressRepository {

	Mono<UUID> createAddress(Address address);
	
	Mono<Address> getAddress(UUID userId);
}
