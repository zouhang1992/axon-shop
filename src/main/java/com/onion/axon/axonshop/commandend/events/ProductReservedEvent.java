package com.onion.axon.axonshop.commandend.events;


import lombok.AllArgsConstructor;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Value
@AllArgsConstructor
public class ProductReservedEvent{

    @TargetAggregateIdentifier
    private String orderId;

    private String productId;

    private int nums;

}
