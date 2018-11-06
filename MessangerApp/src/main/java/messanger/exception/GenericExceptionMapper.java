package messanger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import messanger.model.ErrorMessage;

//@Provider  //register the following class to jax-rs
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage errorMessage  = new ErrorMessage(ex.getMessage(), 500, "provide documentation link");
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorMessage)
				.build();
	}

}
