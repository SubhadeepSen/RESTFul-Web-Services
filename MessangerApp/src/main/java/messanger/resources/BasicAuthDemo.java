package messanger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("secured")
public class BasicAuthDemo {

	@GET
	@Path("basic")
	@Produces(MediaType.TEXT_PLAIN)
	public String secured(){
		return "Basic Authentication is successful";
	}
}
