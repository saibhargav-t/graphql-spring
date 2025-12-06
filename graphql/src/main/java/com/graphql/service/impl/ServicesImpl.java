package com.graphql.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.graphql.constant.Employment;
import com.graphql.dao.UserDAO;
import com.graphql.model.Address;
import com.graphql.model.Users;
import com.graphql.service.AddressService;
import com.graphql.service.Services;

import graphql.schema.DataFetcher;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicesImpl implements Services {

	private final UserDAO dao;
	private final AddressService addressService;

	@Override
	public DataFetcher<CompletableFuture<Users>> getUser() {
		return env -> {
			UUID userId = UUID.fromString(env.getArgument("id"));
			;
			return dao.findUser(userId).toFuture();
		};
	}

	@Override
	public DataFetcher<CompletableFuture<List<Users>>> getAll() {
		return env -> dao.findAll().collectList().toFuture();
	}

	@Override
	public DataFetcher<CompletableFuture<String>> saveUser() {
		return env -> {
			String name = env.getArgument("name");
			String email = env.getArgument("email");
			String gender = env.getArgument("gender");
			int age = env.getArgument("age");
			String street = env.getArgument("street");
			String city = env.getArgument("city");
			String state = env.getArgument("state");
			String country = env.getArgument("country");
			Employment employment = Employment.valueOf(env.getArgument("employment"));
			return dao
					.saveUser(new Users(name, email, age, gender, employment)).flatMap(usersId -> addressService
							.saveAddress(street, city, state, country, usersId).map(addressId -> usersId.toString()))
					.toFuture();

		};
	}

}
