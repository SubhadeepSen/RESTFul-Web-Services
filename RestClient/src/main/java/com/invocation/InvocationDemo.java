package com.invocation;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.model.Message;

public class InvocationDemo {
	public static void main(String[] args) {
		Invocation invocation = prepareRequestForMessagesByYear(2017);
		Response response = invocation.invoke();
		//System.out.println(response.getStatus());
		genericTypeDemo();
	}

	private static Invocation prepareRequestForMessagesByYear(int year) {
		Client client = ClientBuilder.newClient();
		Invocation buildGet = client.target("http://localhost:8081/MessangerApp/webapi/")
								 .path("messages")
								 .queryParam("year", year)
								 .request(MediaType.APPLICATION_XML)
								 .buildGet();
		return buildGet;
	}
	
	private static void genericTypeDemo(){
		Client client = ClientBuilder.newClient();
		List<Message> messageList = client.target("http://localhost:8081/MessangerApp/webapi/")
								 .path("messages")
								 .queryParam("year", 2017)
								 .request(MediaType.APPLICATION_XML)
								 .get(new GenericType<List<Message>>(){});
		System.out.println(messageList);
	}
}
