package com.resource;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("context")
public class ContextAnnotation {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getPathParam(@Context UriInfo uriInfo, @Context HttpServletRequest request){
		URI absolutePath = uriInfo.getAbsolutePath();
		URI baseUri = uriInfo.getBaseUri();
		String path = uriInfo.getPath();
		URI requestUri = uriInfo.getRequestUri();
		
		String contentType = request.getContentType();
		String method = request.getMethod();
		
		String contextResult = "AbsolutePath : " + absolutePath + "\nBaseUri : " + baseUri + 
							   "\nPath : " + path + "\nRequestUri : " + requestUri + 
							   "\nContentType : " + contentType + "\nMethod : " + method;
		return contextResult;
	}
	
}
