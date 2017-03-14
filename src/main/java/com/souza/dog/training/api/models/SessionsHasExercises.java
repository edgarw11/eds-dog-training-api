package com.souza.dog.training.api.models;

import java.io.Serializable;

public class SessionsHasExercises implements Serializable {

	private static final long serialVersionUID = 1L;

	private long sessionID;
	private long exerciseID;

	public SessionsHasExercises() {
		super();
	}

	public SessionsHasExercises(long sessionID, long exerciseID) {
		super();
		this.sessionID = sessionID;
		this.exerciseID = exerciseID;
	}

	public long getSessionID() {
		return sessionID;
	}

	public void setSessionID(long sessionID) {
		this.sessionID = sessionID;
	}

	public long getExerciseID() {
		return exerciseID;
	}

	public void setExerciseID(long exerciseID) {
		this.exerciseID = exerciseID;
	}

}
