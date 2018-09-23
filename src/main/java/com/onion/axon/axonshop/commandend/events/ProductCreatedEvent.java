package com.onion.axon.axonshop.commandend.events;


import lombok.AllArgsConstructor;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Value
@AllArgsConstructor
public class ProductCreatedEvent{

    @TargetAggregateIdentifier
    private String productId;

    private String name;

    private long price;

    private int nums;


}
