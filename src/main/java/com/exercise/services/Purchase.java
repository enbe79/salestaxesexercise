package com.exercise.services;

import com.exercise.JsonBean;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created by BellassaiEP on 29/11/2015.
 */
@Component(value = "purchase")
@Path("/sales")
public class Purchase {

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/add_order")
    public Response addToBasket(JsonBean input) {
        input.setVal2(input.getVal1());
        return Response.ok().entity(input).build();
    }

}
