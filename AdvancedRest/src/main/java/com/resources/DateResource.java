package com.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bean.MyDate;

@Path("date/{dateString}")
public class DateResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getRequestedDate(@PathParam("dateString") MyDate date){
		return date.toString();
	}
}
