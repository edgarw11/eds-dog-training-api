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
import com.souza.dog.training.api.models.Trainer;

@Path("/trainers")
public class TrainerService {	
	
	private static final Logger	log	= Logger.getLogger("TrainerService");

	private void trainerToEntity(Trainer trainer, Entity trainerEntity) {

		trainerEntity.setProperty(Trainer.NAME, trainer.getName());
		trainerEntity.setProperty(Trainer.EMAIL, trainer.getEmail());
	}

	static Trainer entityToTrainer(Entity trainerEntity) {

		Trainer trainer = new Trainer();		

		trainer.setId(trainerEntity.getKey().getId());
		trainer.setName((String) trainerEntity.getProperty(Trainer.NAME));
		trainer.setEmail((String) trainerEntity.getProperty(Trainer.EMAIL));

		return trainer;
	}
	
	@GET
	@Produces("application/json")
	@Path("/{id}")
	public Trainer getTrainer(@PathParam("id") long id){
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		
		Filter idFilter = new FilterPredicate("id", FilterOperator.EQUAL,
				id);
		Query query = new Query("Trainers").setFilter(idFilter);
		Entity trainerEntity = datastore.prepare(query).asSingleEntity();
		if (trainerEntity != null) {
			Trainer trainer = entityToTrainer(trainerEntity);
			return trainer;
		} else {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Trainer> getTrainers() {

		List<Trainer> trainers = new ArrayList<>();
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query query;
		query = new Query("Trainers").addSort(Trainer.ID, SortDirection.ASCENDING);
		List<Entity> trainersEntities = datastore.prepare(query).asList(
				FetchOptions.Builder.withDefaults());
		for (Entity trainerEntity : trainersEntities) {
			Trainer trainer = entityToTrainer(trainerEntity);

			trainers.add(trainer);
		}
		return trainers;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Trainer saveTrainer(Trainer trainer) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Key trainerKey = KeyFactory.createKey("Trainers", "trainerKey");
		Entity trainerEntity = new Entity("Trainers", trainerKey);
		trainerToEntity(trainer, trainerEntity);
		datastore.put(trainerEntity);
		trainer.setId(trainerEntity.getKey().getId());
		return trainer;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Trainer alterTrainer(@PathParam("id") int id, Trainer trainer) {
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Filter idFilter = new FilterPredicate(Trainer.ID, FilterOperator.EQUAL,
				id);
		Query query = new Query("Trainers").setFilter(idFilter);
		Entity trainerEntity = datastore.prepare(query).asSingleEntity();
		if (trainerEntity != null) {
			trainerToEntity(trainer, trainerEntity);
			datastore.put(trainerEntity);
			trainer.setId(trainerEntity.getKey().getId());
			return trainer;
		} else {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Trainer deleteTrainer(@PathParam("id") int id) {
		
		log.fine("Try to delete Trainer with id =["	+	id	+	"]");

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Filter idFilter = new FilterPredicate(Trainer.ID, FilterOperator.EQUAL,
				id);
		Query query = new Query("Trainers").setFilter(idFilter);
		Entity trainerEntity = datastore.prepare(query).asSingleEntity();
		if (trainerEntity != null) {
			datastore.delete(trainerEntity.getKey());
			log.info("Trainer with id =["	+	id	+	"]	successfully deleted.");
			Trainer trainer = entityToTrainer(trainerEntity);
			return trainer;
		} else {
			log.severe	("Error while trying to delete Trainer =["	+	id	+	"].	Trainer not found!");
			throw new WebApplicationException(Status.NOT_FOUND);
		}
	}

}
