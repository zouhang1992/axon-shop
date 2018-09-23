package com.onion.axon.axonshop.commandend.saga;

import com.onion.axon.axonshop.commandend.aggregate.Product;
import com.onion.axon.axonshop.commandend.aggregate.idenditifers.OrderId;
import com.onion.axon.axonshop.commandend.events.OrderCreatedEvent;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ConcurrentHashMap;

import static org.slf4j.LoggerFactory.getLogger;

@Saga
public class OrderSaga {
    private static final Logger LOGGER = getLogger(OrderSaga.class);

    @TargetAggregateIdentifier
    private OrderId orderId;

    private ConcurrentHashMap<String,Product> toResever;

    private ConcurrentHashMap<String,Product> toRollback;

    private int toReserveNums;

    private boolean needRollbackNums;

    @Autowired
    private CommandGateway commandGateway;


    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent event){
        
    }



}
