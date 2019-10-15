package com.mycompany.rhosapp1.rws;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mycompany.rhosapp1.service.impl.CustomersServiceImpl;


@Path("/customer")
public class CustomerResource {
	
	@GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    public Response count() {
		CustomersServiceImpl service = new CustomersServiceImpl();
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObject value = factory.createObjectBuilder().add("count", service.count()).build();
        return Response.ok(value).build();
    }
	
	@GET
	@Path("/all/{pageNum}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response findCustomersPage(@PathParam("pageNum") Integer pageNum)
	{
		CustomersServiceImpl service = new CustomersServiceImpl();
		return Response.status(200).entity(service.findPage(pageNum)).build();
	}
}
