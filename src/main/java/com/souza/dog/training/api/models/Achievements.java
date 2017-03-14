package com.souza.dog.training.api.models;

import java.io.Serializable;

public class Achievements implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String name;
	private String desc;
	private Integer iconID;
	private Integer points;

	public Achievements() {
		super();
	}

	public Achievements(String name, String desc,
			Integer iconID, Integer points) {
		super();
		this.name = name;
		this.desc = desc;
		this.iconID = iconID;
		this.points = points;
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

	public Integer getIconID() {
		return iconID;
	}

	public void setIconID(Integer iconID) {
		this.iconID = iconID;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

}
