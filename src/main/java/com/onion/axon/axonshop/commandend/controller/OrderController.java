package com.onion.axon.axonshop.commandend.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.onion.axon.axonshop.commandend.commands.CreateOrderCommand;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger LOGGER = getLogger(OrderController.class);

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private HttpServletResponse response;

    @RequestMapping(method =RequestMethod.POST)
    public void create(@RequestBody JSONObject input){
        LOGGER.info(input.toString());
        int responseCode = HttpServletResponse.SC_BAD_REQUEST;

        if(input.containsKey("username")&&input.containsKey("products")){
            String username = input.getString("username");
            JSONArray products = input.getJSONArray("products");
            if(!StringUtils.isEmpty(username)&&products.size()>0){
                Map<String, Integer> map = new HashMap<>();
                CreateOrderCommand command = new CreateOrderCommand(username, map);
                for (Object product : products) {
                    JSONObject jsonObject = (JSONObject) product;
                    if(!jsonObject.containsKey("id")||!jsonObject.containsKey("nums")){
                        return;
                    }
                    map.put(jsonObject.getString("id"),jsonObject.getInteger("nums"));
                }
                commandGateway.send(command,LoggingCallback.INSTANCE);
                responseCode=HttpServletResponse.SC_CREATED;
            }
        }
        response.setStatus(responseCode);
    }



}
