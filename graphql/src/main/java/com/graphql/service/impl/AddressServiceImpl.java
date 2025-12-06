package com.graphql.service.impl;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.graphql.dao.AddressRepository;
import com.graphql.model.Address;
import com.graphql.model.Users;
import com.graphql.service.AddressService;

import graphql.schema.DataFetcher;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

	private final AddressRepository addressRepository;

	@Override
	public Mono<String> saveAddress(String street, String city, String state, String country, UUID userId) {
		Address address = new Address();
		address.setStreet(street);
		address.setCity(city);
		address.setState(state);
		address.setCountry(country);
		address.setUserId(userId);
		return addressRepository.createAddress(address).map(Object::toString);
	}

	@Override
	public DataFetcher<CompletableFuture<Address>> getAddress() {
		return env -> {
			Users book = env.getSource();
			return addressRepository.getAddress(book.getId()).toFuture();
		};
	}

}
