package com.souza.dog.training.api.models;

import java.io.Serializable;
import java.util.Date;

public class Pet implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String name;
	private Date birthday;
	private String breed;
	private Double weight;
	private Date lastSession;
	private Integer picId;
	private Integer currentStreak;
	private Integer maxStreak;

	public Pet() {
		super();
	}

	public Pet(String name, Date birthday, String breed, Double weight,
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

	public long getPetID() {
		return id;
	}

	public void setPetID(long id) {
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

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
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
