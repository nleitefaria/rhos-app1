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

import com.mycompany.rhosapp1.service.impl.ProductsServiceImpl;

@Path("/product")
public class ProductResource {
	
	@GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    public Response count() {
		ProductsServiceImpl service = new ProductsServiceImpl();
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObject value = factory.createObjectBuilder().add("count", service.count()).build();
        return Response.ok(value).build();
    }
	
	@GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
		ProductsServiceImpl service = new ProductsServiceImpl();
        return Response.status(200).entity(service.findAll()).build();
    }
	
	@GET
	@Path("/all/{pageNum}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response findCustomersPage(@PathParam("pageNum") Integer pageNum)
	{
		ProductsServiceImpl service = new ProductsServiceImpl();
		return Response.status(200).entity(service.findPage(pageNum)).build();
	}

}
