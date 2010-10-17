package resources;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Simple JAX-RS Custom Exception that returns 404 status.
 *
 * @author aruld@acm.org
 */
public class NotFoundException extends WebApplicationException {

    public static final int NOT_FOUND = 404;

    public NotFoundException() {
        super(notFound().build());
    }

    public NotFoundException(String message) {
        super(Response.status(NOT_FOUND).entity(message).type("text/plain").build());
    }

    static Response.ResponseBuilder notFound() {
        return status(Response.Status.NOT_FOUND);
    }

    static Response.ResponseBuilder status(Response.StatusType status) {
        return Response.status(status);
    }

}