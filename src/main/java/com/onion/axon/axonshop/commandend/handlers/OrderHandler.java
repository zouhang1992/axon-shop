package com.onion.axon.axonshop.commandend.handlers;

import com.onion.axon.axonshop.commandend.commands.CreateOrderCommand;
import com.onion.axon.axonshop.commandend.commands.RollbackOrderCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventBus;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class OrderHandler {

    private static final Logger LOGGER = getLogger(OrderHandler.class);


    @Autowired
    private EventBus eventBus;

    @CommandHandler
    public void handle(CreateOrderCommand command) throws Exception{

    }


    @CommandHandler
    public void handle (RollbackOrderCommand command){


    }




}
