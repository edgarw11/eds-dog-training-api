package com.souza.dog.training.api.models;

import java.io.Serializable;

public class Session implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String ID = "Id";
	public static final String NAME = "Name";
	public static final String DESC = "Desc";

	private long id;
	private String name;
	private String desc;

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

}
