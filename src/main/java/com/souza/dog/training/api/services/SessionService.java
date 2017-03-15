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
import com.souza.dog.training.api.models.Session;

@Path("/sessions")
public class SessionService {

	private static final Logger	log	= Logger.getLogger("SessionService");

	private void sessionToEntity(Session session, Entity sessionEntity) {

		sessionEntity.setProperty(Session.NAME, session.getName());
		sessionEntity.setProperty(Session.DESC, session.getDesc());
	}

	static Session entityToSession(Entity sessionEntity) {

		Session session = new Session();		

		session.setId(sessionEntity.getKey().getId());
		session.setName((String) sessionEntity.getProperty(Session.NAME));
		session.setDesc((String) sessionEntity.getProperty(Session.DESC));

		return session;
	}
	
	@GET
	@Produces("application/json")
	@Path("/{id}")
	public Session getSession(@PathParam("id") long id){
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		
		Filter idFilter = new FilterPredicate("id", FilterOperator.EQUAL,
				id);
		Query query = new Query("Sessions").setFilter(idFilter);
		Entity sessionEntity = datastore.prepare(query).asSingleEntity();
		if (sessionEntity != null) {
			Session session = entityToSession(sessionEntity);
			return session;
		} else {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Session> getSessions() {

		List<Session> sessions = new ArrayList<>();
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query query;
		query = new Query("Sessions").addSort(Session.ID, SortDirection.ASCENDING);
		List<Entity> sessionsEntities = datastore.prepare(query).asList(
				FetchOptions.Builder.withDefaults());
		for (Entity sessionEntity : sessionsEntities) {
			Session session = entityToSession(sessionEntity);

			sessions.add(session);
		}
		return sessions;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Session saveSession(Session session) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Key sessionKey = KeyFactory.createKey("Sessions", "sessionKey");
		Entity sessionEntity = new Entity("Sessions", sessionKey);
		sessionToEntity(session, sessionEntity);
		datastore.put(sessionEntity);
		session.setId(sessionEntity.getKey().getId());
		return session;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Session alterSession(@PathParam("id") int id, Session session) {
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Filter idFilter = new FilterPredicate(Session.ID, FilterOperator.EQUAL,
				id);
		Query query = new Query("Sessions").setFilter(idFilter);
		Entity sessionEntity = datastore.prepare(query).asSingleEntity();
		if (sessionEntity != null) {
			sessionToEntity(session, sessionEntity);
			datastore.put(sessionEntity);
			session.setId(sessionEntity.getKey().getId());
			return session;
		} else {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Session deleteSession(@PathParam("id") int id) {
		
		log.fine("Try to delete Session with id =["	+	id	+	"]");

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Filter idFilter = new FilterPredicate(Session.ID, FilterOperator.EQUAL,
				id);
		Query query = new Query("Sessions").setFilter(idFilter);
		Entity sessionEntity = datastore.prepare(query).asSingleEntity();
		if (sessionEntity != null) {
			datastore.delete(sessionEntity.getKey());
			log.info("Session with id =["	+	id	+	"]	successfully deleted.");
			Session session = entityToSession(sessionEntity);
			return session;
		} else {
			log.severe	("Error while trying to delete Session =["	+	id	+	"].	Session not found!");
			throw new WebApplicationException(Status.NOT_FOUND);
		}
	}
}
