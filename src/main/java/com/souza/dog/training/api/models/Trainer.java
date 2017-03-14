package com.souza.dog.training.api.models;

import java.io.Serializable;

public class Trainer implements Serializable {
	
	public static final String ID = "Id";
	public static final String NAME = "Name";
	public static final String LAST_NAME = "LastName";
	public static final String EMAIL = "Email";
	public static final String PET_ID = "PetId";	

	private static final long serialVersionUID = 1L;

	private long id;
	private String name;
	private String lastName;
	private String email;
	private Integer petId;

	public Trainer() {
		super();
	}

	public Trainer(String name, String lastName, String email) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPetId() {
		return petId;
	}
	
	public void setPetId(Integer petId) {
		this.petId = petId;
	}

}
