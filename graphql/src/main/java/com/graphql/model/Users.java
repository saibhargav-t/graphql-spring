package com.graphql.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.graphql.constant.Employment;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name="USERS")
@NoArgsConstructor
public class Users {

	public Users(String name, String email, int age, String gender, Employment employment) {
		super();
		this.name = name;
		this.email = email;
		this.age = age;
		this.gender = gender;
		this.employment=employment;
	}
	@Id
	private UUID id;
	private String name;
	private String email;
	private int age;
	private String gender;
	private Employment employment;
}
