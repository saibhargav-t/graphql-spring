package com.graphql.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name="USERS")
@NoArgsConstructor
public class Users {

	
	public Users(String name, String email, int age, String gender) {
		super();
		this.name = name;
		this.email = email;
		this.age = age;
		this.gender = gender;
	}
	@Id
	private int id;
	private String name;
	private String email;
	private int age;
	private String gender;
}
