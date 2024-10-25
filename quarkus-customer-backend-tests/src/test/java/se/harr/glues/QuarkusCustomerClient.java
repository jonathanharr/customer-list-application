package se.harr.glues;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import se.harr.models.CustomerDTO;


@RegisterRestClient(configKey = "customer-api")
public interface QuarkusCustomerClient {

    @GET
    @Path("/customer")
    @Produces(MediaType.APPLICATION_JSON)
    Response getCustomerPage(@QueryParam("page") int page, @QueryParam("size") int size);

    @GET
    @Path("/q/health")
    @Produces(MediaType.APPLICATION_JSON)
    Response getHealth();

    @POST
    @Path("/customer")
    @Consumes(MediaType.APPLICATION_JSON) // Specify that it consumes JSON
    Response createCustomer(CustomerDTO customer); // No need for @QueryParam here
}
