package com.mycompany.rhosapp1.rws;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mycompany.rhosapp1.service.ProductsService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/product")
@Api(value = "/product", description = "Products service")
public class ProductResource {
	
	@GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "count", notes = "Get products count")
    public Response count() {
		ProductsService service = new ProductsService();
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObject value = factory.createObjectBuilder().add("count", service.count()).build();
        return Response.ok(value).build();
    }
	
	@GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "all", notes = "Get all products")
    public Response findAll() {
		ProductsService service = new ProductsService();
        return Response.status(200).entity(service.findAll()).build();
    }

}
