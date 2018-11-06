package com.resource;

import java.net.URI;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.bean.Student;

@Path("responseObject")
public class ResponseReturn {
	
	@POST
	@Path("context")
	@Produces(MediaType.APPLICATION_XML)
	public Response getObjectResponseWithContext(@Context UriInfo uriInfo){
		Student student = new Student("Subhadeep","Sen");
		URI absolutePath = uriInfo.getAbsolutePath();
		//URI uri = uriInfo.getAbsolutePathBuilder().path("").build();
		return Response.created(absolutePath)	//this sends the location in response header
					   .entity(student)
					   .build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_XML)
	public Response getObjectResponse(){
		Student student = new Student("Subhadeep","Sen");
		return Response.status(Status.CREATED)
					   .entity(student)
					   .build();
	}
}
