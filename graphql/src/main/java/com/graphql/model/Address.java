package com.graphql.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table("ADDRESS")
@NoArgsConstructor
public class Address {

	@Id
    private UUID id;
    public Address(String street, String city, String state, String country, UUID userId) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
		this.userId=userId;
	}
	private String street;
    private String city;
    private String state;
    private String country;
    
    @Column("USER_ID")
    private UUID userId;
}
