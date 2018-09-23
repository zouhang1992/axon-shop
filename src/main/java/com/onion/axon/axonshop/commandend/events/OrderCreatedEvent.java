package com.onion.axon.axonshop.commandend.events;

import com.onion.axon.axonshop.commandend.aggregate.Product;
import com.onion.axon.axonshop.commandend.aggregate.idenditifers.OrderId;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.Map;

@Value
@AllArgsConstructor
public class OrderCreatedEvent {
    @TargetAggregateIdentifier
    private OrderId orderId;
    private String username;
    private Map<String,Product> products;
}
