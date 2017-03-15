package com.souza.dog.training.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.souza.dog.training.api.models.Exercise;

@Path("/exercises")
public class ExerciseService {
	
	private static final Logger	log	= Logger.getLogger("ExerciseService");

	private void exerciseToEntity(Exercise exercise, Entity exerciseEntity) {

		exerciseEntity.setProperty(Exercise.NAME, exercise.getName());
		exerciseEntity.setProperty(Exercise.DESC, exercise.getDesc());
		exerciseEntity.setProperty(Exercise.INSTRUCTIONS, exercise.getInstructions());

	}

	static Exercise entityToExercise(Entity exerciseEntity) {

		Exercise exercise = new Exercise();		

		exercise.setId(exerciseEntity.getKey().getId());
		exercise.setName((String) exerciseEntity.getProperty(Exercise.NAME));
		exercise.setDesc((String) exerciseEntity.getProperty(Exercise.DESC));
		exercise.setInstructions((String) exerciseEntity.getProperty(Exercise.INSTRUCTIONS));

		return exercise;
	}
	
	@GET
	@Produces("application/json")
	@Path("/{id}")
	public Exercise getExercise(@PathParam("id") long id){
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		
		Filter idFilter = new FilterPredicate("id", FilterOperator.EQUAL,
				id);
		Query query = new Query("Exercises").setFilter(idFilter);
		Entity exerciseEntity = datastore.prepare(query).asSingleEntity();
		if (exerciseEntity != null) {
			Exercise exercise = entityToExercise(exerciseEntity);
			return exercise;
		} else {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Exercise> getExercises() {

		List<Exercise> exercises = new ArrayList<>();
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query query;
		query = new Query("Exercises").addSort(Exercise.ID, SortDirection.ASCENDING);
		List<Entity> exercisesEntities = datastore.prepare(query).asList(
				FetchOptions.Builder.withDefaults());
		for (Entity exerciseEntity : exercisesEntities) {
			Exercise exercise = entityToExercise(exerciseEntity);

			exercises.add(exercise);
		}
		return exercises;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Exercise saveExercise(Exercise exercise) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Key exerciseKey = KeyFactory.createKey("Exercises", "exerciseKey");
		Entity exerciseEntity = new Entity("Exercises", exerciseKey);
		exerciseToEntity(exercise, exerciseEntity);
		datastore.put(exerciseEntity);
		exercise.setId(exerciseEntity.getKey().getId());
		return exercise;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Exercise alterExercise(@PathParam("id") int id, Exercise exercise) {
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Filter idFilter = new FilterPredicate(Exercise.ID, FilterOperator.EQUAL,
				id);
		Query query = new Query("Exercises").setFilter(idFilter);
		Entity exerciseEntity = datastore.prepare(query).asSingleEntity();
		if (exerciseEntity != null) {
			exerciseToEntity(exercise, exerciseEntity);
			datastore.put(exerciseEntity);
			exercise.setId(exerciseEntity.getKey().getId());
			return exercise;
		} else {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Exercise deleteExercise(@PathParam("id") int id) {
		
		log.fine("Try to delete Exercise with id =["	+	id	+	"]");

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Filter idFilter = new FilterPredicate(Exercise.ID, FilterOperator.EQUAL,
				id);
		Query query = new Query("Exercises").setFilter(idFilter);
		Entity exerciseEntity = datastore.prepare(query).asSingleEntity();
		if (exerciseEntity != null) {
			datastore.delete(exerciseEntity.getKey());
			log.info("Exercise with id =["	+	id	+	"]	successfully deleted.");
			Exercise exercise = entityToExercise(exerciseEntity);
			return exercise;
		} else {
			log.severe	("Error while trying to delete Exercise =["	+	id	+	"].	Exercise not found!");
			throw new WebApplicationException(Status.NOT_FOUND);
		}
	}

}
