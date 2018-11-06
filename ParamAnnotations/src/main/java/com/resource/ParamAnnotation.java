package com.resource;

import javax.ws.rs.BeanParam;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.beanParam.BeanParamClass;

@Path("annotations")
public class ParamAnnotation {
	//rest/annotations?queryParam=Query
	//rest/annotations;matrixParam=Matrix
	//@HeaderParam takes the value from request header. It needs to be set in the request header before sending the request
	//@CookieParam takes the value from request cookie.
	
	
	@GET
	@Path("/{pathParam}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPathParam(@PathParam("pathParam") String pathParam){
		return "Path Param : " + pathParam;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getParams(@QueryParam("queryParam") String queryParam,
						    @MatrixParam("matrixParam") String matrixParam,
						    @HeaderParam("headerParam") String headerParam,
						    @CookieParam("cookieParam") String cookieParam){
		
		return "Query Param : " + queryParam + "\nMatrix Param : " + matrixParam +
			   "\nHeader Param : " + headerParam + "\nCookie Param : " + cookieParam;
	}
	
	@GET
	@Path("/{name}/beanParam")
	@Produces(MediaType.TEXT_PLAIN)
	public String getBeanParam(@BeanParam BeanParamClass beanParam){
		String response = "Name: "+beanParam.getPathParam()+" "+beanParam.getSurName()+"\nAge: "+beanParam.getAge();
		return response;
	}
}
