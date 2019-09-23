package com.mycompany.rhosapp1.rws;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mycompany.rhosapp1.service.CustomersService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/customer")
@Api(value = "/customer", description = "Customers service")
public class CustomerResource {
	
	@GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "count", notes = "Get customers count")
    public Response count() {
		CustomersService customersService = new CustomersService();
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObject value = factory.createObjectBuilder().add("CustomersCount", customersService.count()).build();
        return Response.ok(value).build();
    }
	
	@GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "all", notes = "Get all customers")
    public Response findAll() {
        String echo = "Customers";
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObject value = factory.createObjectBuilder().add("Echo message", echo).build();
        return Response.ok(value).build();
    }

}
