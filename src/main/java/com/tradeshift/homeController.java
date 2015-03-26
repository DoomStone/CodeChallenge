package com.tradeshift;

import com.tradeshift.model.HelloResult;
import com.tradeshift.model.MessageModel;
import com.tradeshift.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
@Controller
public class HomeController {

    @Autowired
    private HelloService helloService;

    @GET
    @Path("hello")
    @Produces(MediaType.APPLICATION_JSON)
    public HelloResult Index(@QueryParam("name") String name){
        MessageModel messageModel = new MessageModel();

        if(helloService == null)
            messageModel.setContent("Autowire not working");
        else
            messageModel.setContent(helloService.formatName(name));

        HelloResult result = new HelloResult();
        result.setMessage(messageModel);
        return result;
    }
}
