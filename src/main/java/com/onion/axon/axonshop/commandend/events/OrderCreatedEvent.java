package com.onion.axon.axonshop.commandend.events;

import com.onion.axon.axonshop.commandend.aggregate.Product;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.Map;

@Value
@AllArgsConstructor
public class OrderCreatedEvent {
    @TargetAggregateIdentifier
    private String orderId;
    private String username;
    private Map<String,Product> products;
}
