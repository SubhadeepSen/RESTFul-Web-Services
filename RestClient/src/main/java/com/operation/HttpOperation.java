package com.operation;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.model.Message;

public class HttpOperation {
	private Client client;
	private WebTarget baseTarget;

	public void initClient() {
		client = ClientBuilder.newClient();
		baseTarget = client.target("http://localhost:8081/MessangerApp/webapi/");
	}

	public Message sendGetRequest(int messageId) {
		Message message = null;
		WebTarget messagesTarget = baseTarget.path("messages");
		if (messageId != 0) {
			WebTarget singleMessagesTarget = messagesTarget.path("{messageId}");
			WebTarget resolveTemplate = singleMessagesTarget.resolveTemplate("messageId", String.valueOf(messageId));
			Builder builder = resolveTemplate.request(MediaType.APPLICATION_XML);
			message = builder.get(Message.class);
		}
		return message;
	}

	public List<Message> sendGetRequestForAllMessage() {
		WebTarget messagesTarget = baseTarget.path("messages");
		Builder builder = messagesTarget.request(MediaType.APPLICATION_XML);
		List<Message> messageList = builder.get(new GenericType<List<Message>>(){});
		return messageList;
	}

	public Message sendPosttRequest(Message message) {
		WebTarget messagesTarget = baseTarget.path("messages");
		if (message != null) {
			Builder builder = messagesTarget.request();
			Response postResponse = builder.post(Entity.xml(message));
			message = postResponse.readEntity(Message.class);
		}
		return message;
	}
}
