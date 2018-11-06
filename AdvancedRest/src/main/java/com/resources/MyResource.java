package com.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("{pathParam}/message")
// @Singleton //this annotation creates a single instance, no matter how many times this class
// is being called
// in default life cycle, for each request an instance will be created
public class MyResource {
	@PathParam("pathParam")
	private String pathParam;
	@QueryParam("query")
	private String queryParam;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getMessage() {
		return "Path param: " + pathParam + "\nQuery param: " + queryParam;
	}
}
