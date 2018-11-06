package messanger.authentication;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

@Provider
public class BasicAuthenticationFilter implements ContainerRequestFilter{

	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String AUTHORIZATION_HEADER_BASIC = "Basic ";
	private static final String SECURED_URL_PREFIX = "secured";
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		//This limits jax-rs to authenticate the given URL only, not for entire resources
		//If it finds that the URL contains secured string then only it will authenticate, otherwise it will allow to process request without authentication
		if(requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)){ 
			
		List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER);
		
		if(authHeader != null && authHeader.size() > 0){
			String authToken = authHeader.get(0);
			authToken = authToken.replace(AUTHORIZATION_HEADER_BASIC, "");
			String decodedString = Base64.decodeAsString(authToken);
			StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
			String username = tokenizer.nextToken();
			String password = tokenizer.nextToken();
			
			if("user".equals(username) && "password".equals(password)){
				return;			//returns from the filter and jax-rs is free to proceed further
			}
		}
		Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
											  .entity("user cannot access the resource !")
											  .build();
		
		requestContext.abortWith(unauthorizedStatus); // abort the request from filter, cannot proceed further and send the response/error message
	}
	}
}
