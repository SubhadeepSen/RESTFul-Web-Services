package com.messageBody;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("messagebody")
public class MessageBodyTest {

	@GET
	//@Produces(MediaType.TEXT_PLAIN)
	@Produces("text/shortdate")
	public Date getCurrentDate(){
		return Calendar.getInstance().getTime();
	}
}
