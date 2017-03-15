package com.souza.dog.training.api.models;

import java.io.Serializable;

public class Achievement implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String ID = "Id";
	public static final String NAME = "Name";
	public static final String DESC = "Desc";
	public static final String ICON_ID = "IconID";
	public static final String POINTS = "Points";

	private long id;
	private String name;
	private String desc;
	private Integer iconID;
	private Integer points;

	public Achievement() {
		super();
	}

	public Achievement(String name, String desc,
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
