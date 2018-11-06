package messanger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import messanger.model.ErrorMessage;

@Provider  //register the following class to jax-rs
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage errorMessage  = new ErrorMessage(ex.getMessage(), 404, "provide documentation link");
		return Response.status(Status.NOT_FOUND).entity(errorMessage)
				.build();
	}

}
