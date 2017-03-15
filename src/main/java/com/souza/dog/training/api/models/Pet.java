package com.souza.dog.training.api.models;

import java.io.Serializable;
import java.util.Date;

public class Pet implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String ID = "Id";
	public static final String NAME = "Name";
	public static final String BIRTHDAY = "Birthday";
	public static final String BREED = "Breed";
	public static final String WEIGHT = "Weight";
	public static final String LAST_SESSION = "LastSession";
	public static final String PIC_ID = "PicId";
	public static final String CURRENT_STREAK = "CurrentStreak";
	public static final String MAX_STREAK = "MaxStreak";

	private long id;
	private String name;
	private Date birthday;
	private String breed;
	private Float weight;
	private Date lastSession;
	private Integer picId;
	private Integer currentStreak;
	private Integer maxStreak;

	public Pet() {
		super();
	}

	public Pet(String name, Date birthday, String breed, Float weight,
			Date lastSession, Integer picId, Integer currentStreak,
			Integer maxStreak) {
		super();
		this.name = name;
		this.birthday = birthday;
		this.breed = breed;
		this.weight = weight;
		this.lastSession = lastSession;
		this.picId = picId;
		this.currentStreak = currentStreak;
		this.maxStreak = maxStreak;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Date getLastSession() {
		return lastSession;
	}

	public void setLastSession(Date lastSession) {
		this.lastSession = lastSession;
	}

	public Integer getPicId() {
		return picId;
	}

	public void setPicId(Integer picId) {
		this.picId = picId;
	}

	public Integer getCurrentStreak() {
		return currentStreak;
	}

	public void setCurrentStreak(Integer currentStreak) {
		this.currentStreak = currentStreak;
	}

	public Integer getMaxStreak() {
		return maxStreak;
	}

	public void setMaxStreak(Integer maxStreak) {
		this.maxStreak = maxStreak;
	}

}
