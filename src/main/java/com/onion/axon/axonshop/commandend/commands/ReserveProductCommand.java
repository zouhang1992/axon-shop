package com.onion.axon.axonshop.commandend.commands;


import lombok.AllArgsConstructor;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Value
@AllArgsConstructor
public class ReserveProductCommand {

    @TargetAggregateIdentifier
    private String orderId;

    private String productId;

    private int nums;

}
