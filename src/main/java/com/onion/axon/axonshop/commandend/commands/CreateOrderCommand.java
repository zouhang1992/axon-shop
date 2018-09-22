package com.onion.axon.axonshop.commandend.commands;


import com.onion.axon.axonshop.commandend.aggregate.Product;
import com.onion.axon.axonshop.commandend.aggregate.idenditifers.OrderId;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.Map;

@Value
public class CreateOrderCommand {

    @TargetAggregateIdentifier
    private OrderId orderId;

    private String username;

    private Map<String,Product> products;
}
