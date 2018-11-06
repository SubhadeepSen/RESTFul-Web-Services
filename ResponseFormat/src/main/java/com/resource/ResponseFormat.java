package com.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bean.Student;

@Path("responses")
public class ResponseFormat {
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Student getXmlResponse(){
		return new Student("Subhadeep", "Sen");
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Student getJsonResponse(){
		return new Student("Subhadeep", "Sen");
	}
}
