package com.souza.dog.training.api.services;

import java.util.ArrayList;
import java.util.Date;
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
import com.souza.dog.training.api.models.Pet;

@Path("/pets")
public class PetService {
	
	private static final Logger	log	= Logger.getLogger("PetService");
	
	private void petToEntity(Pet pet, Entity petEntity) {

		petEntity.setProperty(Pet.NAME, pet.getName());
		petEntity.setProperty(Pet.BIRTHDAY, pet.getBirthday());
		petEntity.setProperty(Pet.BREED, pet.getBreed());
		petEntity.setProperty(Pet.WEIGHT, pet.getWeight());
		petEntity.setProperty(Pet.LAST_SESSION, pet.getLastSession());
		petEntity.setProperty(Pet.PIC_ID, pet.getPicId());
		petEntity.setProperty(Pet.CURRENT_STREAK, pet.getCurrentStreak());
		petEntity.setProperty(Pet.MAX_STREAK, pet.getMaxStreak());
		
	}

	static Pet entityToPet(Entity petEntity) {

		Pet pet = new Pet();		

		pet.setId(petEntity.getKey().getId());
		pet.setName((String) petEntity.getProperty(Pet.NAME));
		pet.setBirthday((Date) petEntity.getProperty(Pet.BIRTHDAY));
		pet.setBreed((String) petEntity.getProperty(Pet.BREED));
		pet.setWeight(Float.parseFloat(petEntity.getProperty(Pet.WEIGHT).toString()));
		pet.setLastSession((Date) petEntity.getProperty(Pet.LAST_SESSION));
		pet.setPicId(Integer.parseInt( petEntity.getProperty(Pet.PIC_ID)
				.toString()));
		pet.setCurrentStreak(Integer.parseInt(petEntity.getProperty(Pet.CURRENT_STREAK).toString()));
		pet.setMaxStreak(Integer.parseInt(petEntity.getProperty(Pet.MAX_STREAK).toString()));

		return pet;
	}
	
	@GET
	@Produces("application/json")
	@Path("/{id}")
	public Pet getPet(@PathParam("id") long id){
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		
		Filter idFilter = new FilterPredicate("id", FilterOperator.EQUAL,
				id);
		Query query = new Query("Pets").setFilter(idFilter);
		Entity petEntity = datastore.prepare(query).asSingleEntity();
		if (petEntity != null) {
			Pet pet = entityToPet(petEntity);
			return pet;
		} else {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pet> getPets() {

		List<Pet> pets = new ArrayList<>();
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query query;
		query = new Query("Pets").addSort(Pet.ID, SortDirection.ASCENDING);
		List<Entity> petsEntities = datastore.prepare(query).asList(
				FetchOptions.Builder.withDefaults());
		for (Entity petEntity : petsEntities) {
			Pet pet = entityToPet(petEntity);

			pets.add(pet);
		}
		return pets;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Pet savePet(Pet pet) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Key petKey = KeyFactory.createKey("Pets", "petKey");
		Entity petEntity = new Entity("Pets", petKey);
		petToEntity(pet, petEntity);
		datastore.put(petEntity);
		pet.setId(petEntity.getKey().getId());
		return pet;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Pet alterPet(@PathParam("id") int id, Pet pet) {
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Filter idFilter = new FilterPredicate(Pet.ID, FilterOperator.EQUAL,
				id);
		Query query = new Query("Pets").setFilter(idFilter);
		Entity petEntity = datastore.prepare(query).asSingleEntity();
		if (petEntity != null) {
			petToEntity(pet, petEntity);
			datastore.put(petEntity);
			pet.setId(petEntity.getKey().getId());
			return pet;
		} else {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Pet deletePet(@PathParam("id") int id) {
		
		log.fine("Try to delete Pet with id =["	+	id	+	"]");

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Filter idFilter = new FilterPredicate(Pet.ID, FilterOperator.EQUAL,
				id);
		Query query = new Query("Pets").setFilter(idFilter);
		Entity petEntity = datastore.prepare(query).asSingleEntity();
		if (petEntity != null) {
			datastore.delete(petEntity.getKey());
			log.info("Pet with id =["	+	id	+	"]	successfully deleted.");
			Pet pet = entityToPet(petEntity);
			return pet;
		} else {
			log.severe	("Error while trying to delete Pet =["	+	id	+	"].	Pet not found!");
			throw new WebApplicationException(Status.NOT_FOUND);
		}
	}

}
