package com.graphql.service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import com.graphql.model.Address;

import graphql.schema.DataFetcher;
import reactor.core.publisher.Mono;

public interface AddressService {

	Mono<String> saveAddress(String street, String city, String state, String country, UUID bookId);
	
	DataFetcher<CompletableFuture<Address>> getAddress();
}
