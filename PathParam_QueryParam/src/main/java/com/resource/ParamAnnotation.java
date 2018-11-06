package com.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("params")
public class ParamAnnotation {
	
	@GET
	@Path("/{pathParam}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPathParam(@PathParam("pathParam") String pathParam){
		return "Path Param : " + pathParam;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getQueryParam(@QueryParam("queryParam") String queryParam){
		return "Query Param : " + queryParam;
	}
}
