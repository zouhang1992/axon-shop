package com.onion.axon.axonshop.commandend.commands;


import lombok.AllArgsConstructor;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Value
@AllArgsConstructor
public class CreateProductCommand {

    @TargetAggregateIdentifier
    private String productId;

    private String name;

    private long price;

    private int nums;


}
