package com.onion.axon.axonshop.commandend.events;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreatedEvent{

    @TargetAggregateIdentifier
    private String productId;

    private String name;

    private long price;

    private int nums;


}
