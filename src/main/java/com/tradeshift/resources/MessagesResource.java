package com.tradeshift.resources;

import com.tradeshift.models.HelloResult;
import com.tradeshift.models.MessageModel;
import com.tradeshift.models.RecentResult;
import com.tradeshift.models.dtos.MessageDTO;
import com.tradeshift.services.HelloService;
import com.tradeshift.services.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/messages")
@Controller
public class MessagesResource {
    @Autowired
    private HelloService helloService;

    @Autowired
    private MessagesService messagesService;

    public MessagesResource(){

    }
    public MessagesResource(HelloService helloService, MessagesService messagesService){
        this.helloService = helloService;
        this.messagesService = messagesService;
    }

    @POST
    @Path("names/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public HelloResult names(@PathParam("name") String name){
        String message;
        try{
            message = helloService.formatName(name);
            messagesService.insert(message);
        }
        catch (IllegalArgumentException exp){
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).build());
        }
        HelloResult result = new HelloResult(new MessageModel(message));

        return result;
    }

    @GET
    @Path("recent")
    @Produces(MediaType.APPLICATION_JSON)
    public RecentResult recent(){
        RecentResult model = new RecentResult();
        for(MessageDTO message : messagesService.getMessage(10)){
            model.addMessage(message.getMessage(), message.getCreated());
        }
        return model;
    }
}
