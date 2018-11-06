package messanger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import messanger.beans.MessageFilterBean;
import messanger.model.Message;
import messanger.service.MessageService;

@Path("/messages")
public class MessageResource {

	MessageService messageService = new MessageService();

	/*@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@QueryParam("year") int year, @QueryParam("start") int start,
			@QueryParam("size") int size) { // takes the value from the query parameter [/messages?year=2017 OR/messages?start=0&size=5]
		if (year > 0) {
			return messageService.getAllMessagesForYear(year);
		}
		if (start >= 0 && size > 0) {
			return messageService.getAllMessagespaginated(start, size);
		}
		return messageService.getAllMessages();
	}*/
	
	@GET
	//This version of getMessages() method uses annotation to simplify the above getMessages() method
	@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML})
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
		if (filterBean.getYear() > 0) {
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
			return messageService.getAllMessagespaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}

	/*@POST
	@Consumes(MediaType.APPLICATION_JSON) // consumes the specified format from request body
	@Produces(MediaType.APPLICATION_JSON)
	public Message addMessage(Message message) {
		return messageService.addMessage(message);
	}*/
	
	@POST
	@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML}) 
	@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML})
	//Returns Response instead of only Message along with the status code [201 Created] and the Message entity
	//You can also set cookies and many other things using Response
	public Response addMessage(Message message,@Context UriInfo uriInfo){
		Message newMessage = messageService.addMessage(message);
		
		/*return Response.status(Status.CREATED).
				entity(newMessage).
				build();*/
		
		//below shortcut set the status code to "201 Created" and add the URL of newly created message to the response header
		/*return Response.created(new URI(uriInfo.getAbsolutePath().toString() +"/"+ newMessage.getId())).
				entity(newMessage).
				build();*/
		
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri).
				entity(newMessage).
				build();
	}

	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long messageId, Message message) {
		message.setId(messageId);
		return messageService.updateMessage(message);
	}

	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message deleteMessage(@PathParam("messageId") long messageId) {
		return messageService.removeMessage(messageId);
	}

	@GET
	@Path("/{messageId}")
	@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML})
	public Message getMessage(@PathParam("messageId") long messageId, @Context UriInfo uriInfo) {
		Message message = messageService.getMessage(messageId);
		message.addLink(getUriForSelf(uriInfo, message), "self");
		message.addLink(getUriForProfile(uriInfo, message), "profile");
		message.addLink(getUriForComments(uriInfo, message), "comments");
		return message;
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		 String uri = uriInfo.getBaseUriBuilder()	//returns http://localhost:8085/MessangerApp/webapi/
				.path(MessageResource.class)		//appends /messages [http://localhost:8085/MessangerApp/webapi/messages]
				.path(Long.toString(message.getId()))//appends /{messageId} [http://localhost:8085/MessangerApp/webapi/messages/{messageId}]
				.build()							// builds the complete URI
				.toString();						//converts the built URI to String
		 return uri;
	}
	
	private String getUriForProfile(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()	//returns http://localhost:8085/MessangerApp/webapi/
					.path(ProfileResource.class)	//appends /profiles [http://localhost:8085/MessangerApp/webapi/profiles]
					.path(message.getAuthor())		//appends /{authorName} [http://localhost:8085/MessangerApp/webapi/messages/{authorName}]
					.build()						// builds the complete URI
					.toString();					//converts the built URI to String
		return uri;
	}
	
	private String getUriForComments(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()	//returns http://localhost:8085/MessangerApp/webapi/
					.path(MessageResource.class)	//appends /messages [http://localhost:8085/MessangerApp/webapi/messages]
					.path(MessageResource.class, "getCommentResource")	//appends /{messageId}/comments [http://localhost:8085/MessangerApp/webapi/messages//{messageId}/comments]
					.path(CommentResource.class)	//appends / [http://localhost:8085/MessangerApp/webapi/messages//{messageId}/comments/]
					.resolveTemplate("messageId", message.getId())	//replaces the variable part [{messageId}] with message Id
					.build()
					.toString();
		return uri;
	}

	@Path("/{messageId}/comments")
	//this is a subresource
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
}
