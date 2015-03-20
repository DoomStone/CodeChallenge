package com.tradeshift;

import com.tradeshift.model.helloResult;
import com.tradeshift.model.messageModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
public class homeController {

    @GET
    @Path("hello")
    @Produces(MediaType.APPLICATION_JSON)
    public helloResult Index(@QueryParam("name") String name){
        messageModel messageModel = new messageModel();
        messageModel.setContent("Hello " + name);
        helloResult result = new helloResult();
        result.setMessage(messageModel);
        return result;
    }
}
