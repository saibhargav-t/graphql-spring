package com.graphql.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import com.graphql.service.AddressService;
import com.graphql.service.Services;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring;
import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class Configurations {

	private final Services services;
	private final AddressService addressService;

	@Bean
	ConnectionFactoryInitializer connectionFactoryInitializer(ConnectionFactory connectionFactory) {
		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(connectionFactory);
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator(new ClassPathResource("schema.sql"),
				new ClassPathResource("data.sql"));
		initializer.setDatabasePopulator(populator);
		return initializer;
	}

	@Bean
	GraphQL graphQL() throws IOException {
		SchemaParser schemaParser = new SchemaParser();
		ClassPathResource resource = new ClassPathResource("schema.graphql");
		TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(resource.getInputStream());
		RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
				.type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("getUser", services.getUser()))
				.type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("getAll", services.getAll()))
				.type(TypeRuntimeWiring.newTypeWiring("Mutation").dataFetcher("createUser", services.saveUser()))
				.type(TypeRuntimeWiring.newTypeWiring("Users").dataFetcher("address", addressService.getAddress()))
				.build();
		SchemaGenerator generator = new SchemaGenerator();
		GraphQLSchema executableSchema = generator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
		return GraphQL.newGraphQL(executableSchema).build();
	}
}
