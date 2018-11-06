package messanger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

	@GET
	@Path("/annotations")
	//@MatrixParam takes the value from the URL parameter [/messages;param=hello]
	//@HeaderParam takes the value from the request header
	//@CookieParam takes the value from the cookies
	//@FormParam takes the values of the form from URL
	public String getParamUsingAnnotations(@MatrixParam("param") String matrixParam,
										   @HeaderParam("customHeaderParam") String headerParam,
										   @CookieParam("cookie") String cookie) {
		return "Matrix param: " + matrixParam + "\nHeader param: " + headerParam + "\nCookie param: " +cookie;
	}
	
	@GET
	@Path("/context")
	//@Context is special annotation which is applicable to few classes
	//UriInfo contains a lot of information
	public String getParamUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers) {
		String path = uriInfo.getAbsolutePath().toString();
		String headerParam = headers.getRequestHeaders().toString();
		return "PATH: " + path + "\nHeaderParam: " + headerParam;
}
}
