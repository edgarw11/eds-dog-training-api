package com.souza.dog.training.api.models;

import java.io.Serializable;

public class Exercise implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String ID = "Id";
	public static final String NAME = "Name";
	public static final String DESC = "Desc";
	public static final String INSTRUCTIONS = "Instructions";
	
	private long id;
	private String name;
	private String desc;
	private String instructions;

	public Exercise() {
		super();
	}

	public Exercise(String name, String desc, String instructions) {
		super();
		this.name = name;
		this.desc = desc;
		this.instructions = instructions;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

}
