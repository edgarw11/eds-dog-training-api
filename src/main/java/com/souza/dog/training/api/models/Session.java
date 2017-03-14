package com.souza.dog.training.api.models;

import java.io.Serializable;
import java.util.List;

public class Session implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String name;
	private String desc;
	private List<Exercise> exercises;

	public Session() {
		super();
	}

	public Session(String name, String desc) {
		super();
		this.name = name;
		this.desc = desc;
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

	public List<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}

}
