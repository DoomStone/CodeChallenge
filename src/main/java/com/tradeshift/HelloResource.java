package com.tradeshift;

import com.tradeshift.model.HelloResult;
import com.tradeshift.model.MessageModel;
import com.tradeshift.model.RecentResult;
import com.tradeshfit.model.dao.MessageDAO;
import com.tradeshfit.model.dao.MessagesDAO;
import com.tradeshift.service.HelloService;
import com.tradeshift.service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/messages")
@Controller
public class HelloResource {
    @Autowired
    private HelloService helloService;

    @Autowired
    private MessagesService messagesService;

    public HelloResource(){

    }
    public HelloResource(HelloService helloService, MessagesService messagesService){
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
        catch (NullPointerException exp){
            message = "Error: Name can not be empty";
        }
        HelloResult result = new HelloResult(new MessageModel(message));

        return result;
    }

    @GET
    @Path("recent")
    @Produces(MediaType.APPLICATION_JSON)
    public RecentResult recent(){
        RecentResult model = new RecentResult();
        for(MessageDAO message : messagesService.getMessage(10)){
            model.addMessage(message.getMessage(), message.getCreated());
        }
        return model;
    }
}
