package com.tradeshift;

import com.tradeshift.model.HelloResult;
import com.tradeshift.model.MessageModel;
import com.tradeshift.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/messages")
@Controller
public class HelloResource {

    @Autowired
    private HelloService helloService;

    @POST
    @Path("names/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public HelloResult names(@PathParam("name") String name){
        HelloResult result = new HelloResult(new MessageModel(helloService.formatName(name)));
        return result;
    }
}
